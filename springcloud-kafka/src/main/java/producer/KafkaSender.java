package producer;

import Bean.UserLog;
import com.alibaba.fastjson.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class KafkaSender {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();
    //发送消息方法
    public void send() {
       UserLog userLog=new UserLog();
        userLog.setState(String.valueOf(System.currentTimeMillis()));
        userLog.setUserid(UUID.randomUUID().toString());
        userLog.setUsername(String.valueOf(new Date()));

        log.info("+++++++++++++++++++++  message = {}", gson.toJson(userLog));
        kafkaTemplate.send("wang", gson.toJson(userLog));
    }
}
