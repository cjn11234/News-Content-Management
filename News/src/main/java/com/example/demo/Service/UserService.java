package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Exception.registerException;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean exists(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    public User findByNameAndPassword(User user) {

        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    public User findByName(User user) {

        return userRepository.findByUsername(user.getUsername());
    }
    public boolean insert(User user) {
        if(exists(user)){
            throw new registerException("账户已存在");
        }
        else {
            userRepository.save(user);
        }
        return true;
    }


}