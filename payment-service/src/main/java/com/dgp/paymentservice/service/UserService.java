package com.dgp.paymentservice.service;

import com.dgp.paymentservice.model.User;
import com.dgp.paymentservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }
}
