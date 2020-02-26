package com.itcodai.course01.service.impl;

import com.itcodai.course01.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ListenerUserService {

    /**
     * 获取用户信息
     * @return
     */
    public User getUser() {
        // 实际中会根据具体的业务场景，从数据库中查询对应的信息
        return new User(1L, "倪升武", "123456");
    }
}
