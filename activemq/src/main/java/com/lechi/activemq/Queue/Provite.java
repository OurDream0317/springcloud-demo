package com.lechi.activemq.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

@Component
public class Provite {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000)
    public void privatemessage() {
        jmsMessagingTemplate.convertAndSend(topic, "*******" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("生产消息成功！");
    }

}
