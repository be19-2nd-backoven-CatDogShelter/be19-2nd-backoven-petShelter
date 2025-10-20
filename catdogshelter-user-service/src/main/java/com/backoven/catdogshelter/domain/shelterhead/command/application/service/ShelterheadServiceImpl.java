package com.backoven.catdogshelter.domain.shelterhead.command.application.service;

import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.shelterhead.command.application.dto.ShelterheadDTO;
import com.backoven.catdogshelter.domain.shelterhead.command.domain.aggregate.entity.ShelterheadEntity;
import com.backoven.catdogshelter.domain.shelterhead.command.domain.repository.ShelterheadRepository;
import com.backoven.catdogshelter.domain.user.command.application.service.UserRedisService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShelterheadServiceImpl implements ShelterheadService {

    ShelterheadRepository shelterheadRepository;
    private final ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ShelterheadRedisService shelterheadRedisService;
    private final JavaMailSender mailSender; // 이메일 전송용

    @Autowired
    public ShelterheadServiceImpl(ShelterheadRepository shelterheadRepository,
                                  ModelMapper modelMapper,
                                  BCryptPasswordEncoder bCryptPasswordEncoder,
                                  ShelterheadRedisService shelterheadRedisService,
                                  JavaMailSender mailSender) {
        this.shelterheadRepository = shelterheadRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.shelterheadRedisService = shelterheadRedisService;
        this.mailSender = mailSender;
    }

    @Override
    public ShelterheadDTO getShelter_headById(String memNo) {
        ShelterheadEntity shelterHead = shelterheadRepository.findById(Integer.parseInt(memNo)).get();
        ShelterheadDTO shelterHeadDTO = modelMapper.map(shelterHead, ShelterheadDTO.class);
        return  shelterHeadDTO;
    }
    @Override
    public void registUser(ShelterheadDTO shelter_head) {

//        shelter_head.setHeadAccount(UUID.randomUUID().toString());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // DTO → Entity 매핑
        ShelterheadEntity shelterHeadEntity =
                modelMapper.map(shelter_head, ShelterheadEntity.class);
        shelterHeadEntity.setHeadPassword(bCryptPasswordEncoder.encode(shelter_head.getPwd()));

        shelterheadRepository.save(shelterHeadEntity);
    }

    /* 그럼 이제 loadUserByUsername는 repository를 통해 DB에 아이디가 맞는지 안 맞는지 확인한다 */
    /* spring security 사용 시 프로바이더에서 활용 할 로그인용 메소드(UserDetails 타입을 반환하는 메소드) */
    @Override
    public UserDetails loadUserByUsername(String headAccount) throws UsernameNotFoundException {

        // 쿼리 메소드를 활용한 email where절 활용
        ShelterheadEntity loginShelter_head = shelterheadRepository.findByHeadAccount(headAccount);

        if(loginShelter_head == null){
            throw new UsernameNotFoundException(headAccount + "아이디 유저는 존재하지 않습니다.");
        }

        /*  (이 전에 유저와 권한를 조인햇다는 가정하에, 우리는 등급 테이블이 있으니까 추후
             등급 테이블에 있는 등급들을 바탕으로 하면 좋을거 같다)
             DB에서 조회 된 해당 Email의 회원이 가진 권한들을 가져와  List<GrantedAuthority>로 전환 */
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ENTERPRISE"));


        /* UserDetails 타입에 있는 User()은 여러가지 기능 즉 로그인 시에 받을 수 있는 데이터를 정할 수 있다. */
        return new User(loginShelter_head.getHeadAccount(),
                loginShelter_head.getHeadPassword(), true, true,true,
                true, grantedAuthorities);
    }

    @Override
    public void sendVerificationCode(String headAccount) {
        // 1. DB에서 사용자 조회
        ShelterheadEntity foundShelterhead = shelterheadRepository.findByHeadAccount(headAccount);
        if (foundShelterhead == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
        }

        // 3. 인증 코드 생성 (6자리 난수)
        String verificationCode = String.valueOf((int)(Math.random() * 900000) + 100000);
        log.info("생성된 인증코드: {}", verificationCode);

        // 4. Redis에 저장 (key=email, value=code, TTL=5분)
        shelterheadRedisService.saveAuthCode(foundShelterhead.getEmail(), verificationCode, 5);

        // 5. 이메일 발송
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(foundShelterhead.getEmail());
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
    public void resetUserPassword(String headAccount, String verificationCode, String newPassword) {
        // 1. 사용자가 존재하는지 확인
        ShelterheadEntity shelterhead = shelterheadRepository.findByHeadAccount(headAccount);
        if (shelterhead == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        // 2. Redis에서 인증번호 꺼내오기
        String storedCode = shelterheadRedisService.getAuthCode(shelterhead.getEmail());
        if (storedCode == null) {
            throw new IllegalArgumentException("인증번호가 만료되었거나 존재하지 않습니다.");
        }

        // 3. 인증번호 검증
        if (!storedCode.equals(verificationCode)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        // 4. 새 비밀번호 암호화 후 저장
        String encryptedPwd = bCryptPasswordEncoder.encode(newPassword);
        shelterhead.setHeadPassword(encryptedPwd);
        shelterheadRepository.save(shelterhead);

        // 5. Redis에서 인증번호 삭제 (일회용)
        shelterheadRedisService.deleteAuthCode(shelterhead.getEmail());
    }

}
