package com.city.security.serviece.impl;

import com.city.security.entity.User;
import com.city.security.respository.UserRepository;
import com.city.security.serviece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Integer id) {

        return userRepository.findUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
