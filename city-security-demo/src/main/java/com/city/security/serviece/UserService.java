package com.city.security.serviece;

import com.city.security.entity.User;

public interface UserService {
    public User findUserById(Integer id);


    public void saveUser(User user);
}
