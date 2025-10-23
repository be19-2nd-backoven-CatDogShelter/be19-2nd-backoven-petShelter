package com.backoven.catdogshelter.domain.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfig {
    // DTO <-> DTO <-> Entity
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    // 설명. BCrypt 단방향 암호화를 위해 bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}