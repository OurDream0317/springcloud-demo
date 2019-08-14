package com.lechi.activemq.scbscribuers;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;


public class custome {
    private static final String MQ_URL = "tcp://192.168.189.128:61616";
    private static final String TOPIC_NAME = "topic";

    @Test
    public void method() throws JMSException {
        System.out.println("我是4号消费者");
        //创建工厂，获取连接，并开启
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("z4");
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建消息消费者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        Message message = topicSubscriber.receive();
        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("接收到持久化的消息为：" + textMessage.getText());
            message = topicSubscriber.receive();
            System.out.println(message);
        }
        session.close();
        connection.close();
    }
}
