package com.example.nerdy.service.impl;

import com.example.nerdy.constant.ExceptionMessage;
import com.example.nerdy.dto.UserRegistrationDto;
import com.example.nerdy.entity.User;
import com.example.nerdy.exceptions.InvalidEmailException;
import com.example.nerdy.exceptions.InvalidPasswordException;
import com.example.nerdy.exceptions.UserAlreadyExistException;
import com.example.nerdy.mapper.UserRegistrationMapper;
import com.example.nerdy.repository.TaskRepository;
import com.example.nerdy.repository.UserRepository;
import com.example.nerdy.security.JWTUser;
import com.example.nerdy.service.UserService;
import com.example.nerdy.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TaskRepository articleRepository;
    private UserRegistrationMapper userRegistrationMapper;
    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           TaskRepository articleRepository,
                           UserRegistrationMapper userRegistrationMapper,
                           Validator validator) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.userRegistrationMapper = userRegistrationMapper;
        this.validator = validator;
    }

    @Override
    public void registration(UserRegistrationDto userRegistrationDto) {
        if (!validator.validateEmail(userRegistrationDto.getEmail())) {
            throw new InvalidEmailException(ExceptionMessage.INVALID_EMAIL);
        }
        if (!validator.validatePassword(userRegistrationDto.getPassword())) {
            throw new InvalidPasswordException(ExceptionMessage.INCORRECT_PASSWORD);
        }
        if (!(userRepository.findUserByEmail(userRegistrationDto.getEmail()) == null)) {
            throw new UserAlreadyExistException(ExceptionMessage.USER_ALREADY_EXIST
                    + userRegistrationDto.getEmail()
                    + "!!!");
        }
        User user = userRegistrationMapper.convertToEntity(userRegistrationDto);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean checkPasswordMatches(int id, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.getOne(id);
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser jwtUser = (JWTUser) authentication.getPrincipal();
        return jwtUser.getUser();
    }
}
