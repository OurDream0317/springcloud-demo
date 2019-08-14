package com.lechi.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.*;


public class activemqFactory {
    private static final String MQ_URL = "tcp://192.168.189.128:61616";
    private static final String MQ_NAME = "queue";

    @Test
    public void method() throws JMSException {
        //创建工厂，获取连接，并开启
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(MQ_NAME);
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //通过消息生产者生产3条消息放到queue中
        for (int i = 10; i <= 16; i++) {
            TextMessage textMessage = session.createTextMessage("msg====================" + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息生产完毕 ，已发送到queue中");

    }
}
