package com.itcodai.course01.controller;

import com.itcodai.course01.common.BusinessErrorException;
import com.itcodai.course01.common.BusinessMsgEnum;
import com.itcodai.course01.common.ExceptionJsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception")
@Slf4j
public class ExceptionController {

    @PostMapping("/test")
    public ExceptionJsonResult test(@RequestParam("name") String name,
                           @RequestParam("pass") String pass) {
        log.info("name：{}", name);
        log.info("pass：{}", pass);
        return new ExceptionJsonResult();
    }

    @GetMapping("/business")
    public ExceptionJsonResult testException() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return new ExceptionJsonResult();
    }

}
