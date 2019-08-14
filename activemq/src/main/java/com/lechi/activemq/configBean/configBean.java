package com.lechi.activemq.configBean;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
@EnableJms
public class configBean {
    @Value("${myqueue}")
    private String myqueue;

    @Bean
    private Topic topic() {
        return new ActiveMQTopic(myqueue);
    }
}
