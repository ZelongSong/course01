package com.itcodai.course01.service.impl;

import com.itcodai.course01.dao.UserMapper;
import com.itcodai.course01.entity.User;
import com.itcodai.course01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        // 插入用户信息
        userMapper.insertUser(user);
        // 手动抛出异常
        throw new RuntimeException();
    }

    @Override
    public void insertUser2(User user) {

    }

    @Override
    public void insertUser3(User user) {

    }

 /*   @Override
    @Transactional
    public void insertUser2(User user) throws Exception {
        // 插入用户信息
        userMapper.insertUser(user);
        // 手动抛出异常
        throw new SQLException("数据库异常");
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void insertUser4(User user) {
        // 实际中的具体业务……
        userMapper.insertUser(user);
    }

    @Override
    public Set<String> getRoles(String userName) {
        return null;
    }

    @Override
    public Set<String> getPermissions(String userName) {
        return null;
    }

}
