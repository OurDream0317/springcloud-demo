package com.lechi.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;


public class custome {
    private static final String MQ_URL = "tcp://192.168.189.128:61616";
    private static final String TOPIC_NAME = "topic";

    @Test
    public void method() throws JMSException {
        System.out.println("我是3号消费者");
        //创建工厂，获取连接，并开启
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建消息消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
      /*  while (true){
            TextMessage message = (TextMessage) messageConsumer.receive();
            if(null!=message){
                System.out.println("接收到的消息是====="+message.getText());
            }else{
                break;
            }
        }*/
        //通过监听器来消费信息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收到的消息是=====" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
