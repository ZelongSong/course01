package com.itcodai.course01.controller;

import com.itcodai.course01.common.UnInterception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/test")
    //@UnInterception
    public String test() {
        return "hello";
    }
}
