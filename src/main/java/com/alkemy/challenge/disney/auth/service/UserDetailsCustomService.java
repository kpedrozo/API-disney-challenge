package com.alkemy.challenge.disney.auth.service;

import com.alkemy.challenge.disney.auth.dto.UserDTO;
import com.alkemy.challenge.disney.auth.entity.UserEntity;
import com.alkemy.challenge.disney.auth.repository.UserRepository;
import com.alkemy.challenge.disney.exception.ErrorEnum;
import com.alkemy.challenge.disney.exception.UserExists;
import com.alkemy.challenge.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UserExists(ErrorEnum.USERINVALID.getMessage());
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    private boolean existInRepo(String username) {
        UserEntity user = new UserEntity();
        user = userRepository.findByUsername(username);
        return user != null;
    }

    public boolean save(UserDTO userDTO) {
        if (existInRepo(userDTO.getUsername())) {
            throw new UserExists(ErrorEnum.USEREXISTS.getMessage());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity = this.userRepository.save(userEntity);
        if (userEntity != null){
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
        }
}

