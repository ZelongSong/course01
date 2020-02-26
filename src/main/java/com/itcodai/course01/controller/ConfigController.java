package com.itcodai.course01.controller;

import com.itcodai.course01.common.MicroServiceUrl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Slf4j
public class ConfigController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    @Value("${url.orderUrl}")
    private String orderUrl;

    @Resource
    private MicroServiceUrl microServiceUrl;

    @RequestMapping("/config")
    public String testConfig() {
        log.info("=====获取的订单服务地址为：{}", microServiceUrl.getOrderUrl());
        log.info("=====获取的用户服务地址为：{}", microServiceUrl.getUserUrl());
        log.info("=====获取的购物车服务地址为：{}", microServiceUrl.getShoppingUrl());

        return "success";
    }
}
