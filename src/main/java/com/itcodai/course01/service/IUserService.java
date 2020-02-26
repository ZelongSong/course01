package com.itcodai.course01.service;

import com.itcodai.course01.entity.User;

import java.util.Set;

public interface IUserService {

    User getUserByName(String username);

    void insertUser(User user);

    void insertUser2(User user);

    void insertUser3(User user);

    void insertUser4(User user);

    Set<String> getRoles(String userName);

    Set<String> getPermissions(String userName);
}
