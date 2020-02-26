package com.itcodai.course01.mq;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.attribute.standard.Destination;

/**
 * 消息发送者
 * @author shengwu ni
 */
@Service
public class MsgProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
