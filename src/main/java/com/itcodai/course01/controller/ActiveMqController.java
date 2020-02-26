package com.itcodai.course01.controller;

import com.itcodai.course01.mq.MsgProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.print.attribute.standard.Destination;

@RestController
@RequestMapping("/activemq")
@Slf4j
public class ActiveMqController {

    //private static final Logger logger = LoggerFactory.getLogger(ActiveMqController.class);

    @Resource
    private MsgProducer producer;

    @Resource
    private Destination queue;

    @Resource
    private Destination topic;

    @GetMapping("/send/queue")
    public String sendQueueMessage() {

        log.info("===开始发送点对点消息===");
        producer.sendMessage(queue, "Queue: hello activemq!");
        return "success";
    }

    @GetMapping("/send/topic")
    public String sendTopicMessage() {

        log.info("===开始发送订阅消息===");
        producer.sendMessage(topic, "Topic: hello activemq!");
        return "success";
    }
}
