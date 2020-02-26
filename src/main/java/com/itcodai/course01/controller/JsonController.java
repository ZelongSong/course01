package com.itcodai.course01.controller;

import com.itcodai.course01.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json")
@Log4j2
public class JsonController {
    @RequestMapping("/user")
    public User getUser() {
        return new User((long) 1, "倪升武", "123456");
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User((long)1, "倪升武", null);
        User user2 = new User((long)2, "达人课", "123456");
        userList.add(user1);
        userList.add(user2);
        log.info(userList);
        return userList;
    }

    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        User user = new User((long)1, "倪升武", null);
        map.put("作者信息", user);
        map.put("博客地址", "http://blog.itcodai.com");
        map.put("CSDN地址", "http://blog.csdn.net/eson_15");
        map.put("粉丝数量", 4153);
        log.info(map);
        return map;
    }
}
