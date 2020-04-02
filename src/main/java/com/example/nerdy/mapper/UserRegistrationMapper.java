package com.example.nerdy.mapper;

import com.example.nerdy.dto.UserRegistrationDto;
import com.example.nerdy.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {
    public User convertToEntity (UserRegistrationDto dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
