package com.backoven.catdogshelter.domain.shelter_head.command.application.service;

import com.backoven.catdogshelter.domain.shelter_head.command.application.dto.Shelter_headDTO;
import com.backoven.catdogshelter.domain.shelter_head.command.domain.aggregate.entity.Shelter_headEntity;
import com.backoven.catdogshelter.domain.shelter_head.command.domain.repository.Shelter_headRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Service
@Slf4j
public class Shelter_headServiceImpl implements Shelter_headService {

    Shelter_headRepository shelter_headRepository;
    private final ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public Shelter_headServiceImpl(Shelter_headRepository shelter_headRepository,
                                   ModelMapper modelMapper,
                                   BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.shelter_headRepository = shelter_headRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override
    public Shelter_headDTO getShelter_headById(String memNo) {
        Shelter_headEntity shelterHead = shelter_headRepository.findById(Integer.parseInt(memNo)).get();
        Shelter_headDTO shelterHeadDTO = modelMapper.map(shelterHead, Shelter_headDTO.class);
        return  shelterHeadDTO;
    }

    @Override
    public void registUser(Shelter_headDTO shelter_head) {

        shelter_head.setHeadAccount(UUID.randomUUID().toString());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Shelter_headEntity shelterHeadEntity =
                modelMapper.map(shelter_head, Shelter_headEntity.class);

        shelterHeadEntity.setHeadPassword(bCryptPasswordEncoder.encode(shelter_head.getPwd()));

        shelter_headRepository.save(shelterHeadEntity);
    }

    /* 그럼 이제 loadUserByUsername는 repository를 통해 DB에 아이디가 맞는지 안 맞는지 확인한다 */
    /* spring security 사용 시 프로바이더에서 활용 할 로그인용 메소드(UserDetails 타입을 반환하는 메소드) */
    @Override
    public UserDetails loadUserByUsername(String headAccount) throws UsernameNotFoundException {

        // 쿼리 메소드를 활용한 email where절 활용
        Shelter_headEntity loginShelter_head = shelter_headRepository.findByHeadAccount(headAccount);

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
}
