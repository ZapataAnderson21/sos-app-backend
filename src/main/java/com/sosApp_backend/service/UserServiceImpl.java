package com.sosApp_backend.service;

import com.sosApp_backend.model.User;
import com.sosApp_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public List<User> searchByUsername(String username){
        return userRepository.findByUsernameContaining(username);
    }

}
