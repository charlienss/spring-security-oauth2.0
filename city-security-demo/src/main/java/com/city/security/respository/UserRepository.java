package com.city.security.respository;

import com.city.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUserById(Integer id);

}
