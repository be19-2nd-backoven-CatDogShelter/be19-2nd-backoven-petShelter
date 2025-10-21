package com.backoven.catdogshelter.domain.user.command.application.service;

import com.backoven.catdogshelter.common.entity.QuestionCategoryEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.enumeration.UserStatus;
import com.backoven.catdogshelter.domain.user.command.domain.repository.QuestionCategoryRepository;
import com.backoven.catdogshelter.domain.user.command.domain.repository.SigunguRepository;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
import com.backoven.catdogshelter.domain.user.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SigunguRepository sigunguRepository;
    private final QuestionCategoryRepository questionCategoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final UserRedisService redisService;
    private final JavaMailSender mailSender; // 이메일 전송용



    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           SigunguRepository sigunguRepository,
                           QuestionCategoryRepository questionCategoryRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, UserRedisService redisService,
                           JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.sigunguRepository = sigunguRepository;
        this.questionCategoryRepository = questionCategoryRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.redisService = redisService;
        this.mailSender = mailSender;
    }

    // 비밀번호 암호화
    @Override
    public void registUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", UserEntity.class);

        // UserDTO로 넘어온 사용자의 암호(평문)를 BCrypt 암호화(다이체스트)
        userEntity.setEncryptPwd(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        userRepository.save(userEntity);
    }

    /* 설명. 단순 회원정보 조회에서 =>
    *   + 회원정보 + 회원의 주문내약(Order(다른 도메인)) */
    @Override
    public UserDTO getUserById(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        /* 설명. 회원이 주문한 내역은 Order서비스에서
        *       Feign client 방식으로 조회해서 가져오기*/
//        List<ResponseOrderDTO> orderList = orderServiceClient.getUserOrders(memNo);
//        userDTO.setOrders(orderList);

        return userDTO;
    }

    @Transactional
    public UserDTO modifyUser(int userId, RequestModifyUserDTO updatedUser) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 수정할 값만 반영
        if (updatedUser.getDetailAddress() != null) {
            userEntity.setDetailAddress(updatedUser.getDetailAddress());
        }
        if (updatedUser.getSigunguId() != null) {
            SigunguEntity sigungu = sigunguRepository.findById(updatedUser.getSigunguId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 시군구가 존재하지 않습니다."));
            userEntity.setSigungu(sigungu);
        }

        userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDTO.class);
    }

    // 유저서비스디테일 물려받아서 오버라이딩
    /* 설명. spring security 사용 시 프로바이더에서 활용할 로그인용 메소드
    *       -> UserDetails 타입을 반환하는 메소드(상속받아서 오버라이딩) */
    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        /* 설명. 넘어온 email과 일치하는 email을 가진 회원을 조회해서
        *       UserEntity로 반환(조회) */
        // 쿼리 메소드를 활용한 email where절 활용
        UserEntity loginUser = userRepository.findByUserAccount(userAccount);
        /* 설명. 사용자가 로그인 시 아이디을 잘못 입력 했다면 */
        if(loginUser == null){
            throw new UsernameNotFoundException(userAccount + " 아이디의 유저는 존재하지 않습니다.");
        }

        /* 설명. DB에서 조회 된 해당 회원이
        *       가진 권한들을 가져와 List<GrantedAuthority>로 전환*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new CustomUserDetails(
                loginUser.getUserId(),
                loginUser.getUserAccount(),
                loginUser.getEncryptPwd(),
                grantedAuthorities
        );
        // 이 객체에 username, password, authorities 빼고 나머지 담음
//        MemberDTO memberDTO = new MemberDTO();
//        UserImpl userImpl =
//                new UserImpl(loginUser.getEmail(),
//                        loginUser.getEncryptPwd(),
//                        grantedAuthorities);
//        userImpl.setDetails(memberDTO);

//        return userImpl;
    }

    @Override
    @Transactional
    public void modifyUserPassword(int userId, RequestModifyPasswordUserDTO updatedUser) {
        UserEntity foundUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        // 기존 비밀번호와 일치하지 않다면 예외처리
        if(!bCryptPasswordEncoder.matches(updatedUser.getCurrentPwd(), foundUser.getEncryptPwd())){
            throw new IllegalArgumentException();
        }
        // 새로운 비밀번호가 기존비밀번호와 일치한다면 예외처리
        if (bCryptPasswordEncoder.matches(updatedUser.getNewPwd(), foundUser.getEncryptPwd())) {
            throw new IllegalArgumentException("새 비밀번호가 기존과 동일합니다.");
        }
    }

    @Override
    @Transactional
    public void deleterUserByPassword(int userId, String currentPwd) {
        UserEntity foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 기존 비밀번호와 일치하지 않다면 예외처리
        if (!bCryptPasswordEncoder.matches(currentPwd, foundUser.getEncryptPwd())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 탈퇴 -> soft delete (상태 변경)
        foundUser.setUserStatus(UserStatus.CANCEL);
    }

    // 이메일로 인증코드를 발급받는 코드
    @Override
    public void sendVerificationCode(String userAccount, String email) {
        // 1. DB에서 사용자 조회
        UserEntity foundUser = userRepository.findByUserAccount(userAccount);
        if (foundUser == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        // 2. 이메일 일치 여부 확인
        if (foundUser.getEmail() == null || !foundUser.getEmail().trim().equalsIgnoreCase(email.trim())) {
            throw new IllegalArgumentException("등록된 이메일과 일치하지 않습니다.");
        }

        // 3. 인증 코드 생성 (6자리 난수)
        String verificationCode = String.valueOf((int)(Math.random() * 900000) + 100000);
        log.info("생성된 인증코드: {}", verificationCode);

        // 4. Redis에 저장 (key=email, value=code, TTL=5분)
        redisService.saveAuthCode(foundUser.getEmail(), verificationCode, 5);

        // 5. 이메일 발송
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(foundUser.getEmail());
            message.setSubject("[CatDogShelter] 비밀번호 재설정 인증코드 안내");
            message.setText("요청하신 인증코드는 [" + verificationCode + "] 입니다.\n"
                    + "5분 안에 입력해주세요.");
            mailSender.send(message);
        } catch (Exception e) {
            log.error("이메일 발송 실패: {}", e.getMessage());
            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.");
        }
    }

    // 발급받은 이메일 코드를 통해 새로운 비밀번호 입력
    @Override
    @Transactional
    public void resetUserPassword(String userAccount, String verificationCode, String newPassword) {
        // 1. 사용자가 존재하는지 확인
        UserEntity user = userRepository.findByUserAccount(userAccount);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        // 2. Redis에서 인증번호 꺼내오기
        String storedCode = redisService.getAuthCode(user.getEmail());
        if (storedCode == null) {
            throw new IllegalArgumentException("인증번호가 만료되었거나 존재하지 않습니다.");
        }

        // 3. 인증번호 검증
        if (!storedCode.equals(verificationCode)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        // 4. 새 비밀번호 암호화 후 저장
        String encryptedPwd = bCryptPasswordEncoder.encode(newPassword);
        user.setEncryptPwd(encryptedPwd);
        userRepository.save(user);

        // 5. Redis에서 인증번호 삭제 (일회용)
        redisService.deleteAuthCode(user.getEmail());
    }

}
