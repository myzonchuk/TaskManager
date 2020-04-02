package com.example.nerdy.service;

import com.example.nerdy.dto.UserRegistrationDto;
import com.example.nerdy.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    void registration(UserRegistrationDto userRegistrationDto);

    User findUserByEmail(String email);

    boolean checkPasswordMatches (int id, String password);

    User getCurrentUser();
}
