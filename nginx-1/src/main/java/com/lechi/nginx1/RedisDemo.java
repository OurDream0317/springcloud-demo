package com.lechi.nginx1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisDemo {
    @Value("${server.port}")
    private String port;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/getCount")
    public String getCount() {
        int count = 0;
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid + "=============================");
        String lockkey = "lockkey";
        try {
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, uuid, 300, TimeUnit.SECONDS);  //setnx
            if (!result) {
                return "error";
            }
            count = Integer.parseInt(stringRedisTemplate.opsForValue().get("count"));
            if (count > 0) {
                count--;
                stringRedisTemplate.opsForValue().set("count", count + "");
                System.out.println("当前余票为" + "====" + count);
            } else {
                System.out.println("当前余票为0");
            }
        } catch (Exception e) {

        } finally {
            if (uuid.equals(stringRedisTemplate.opsForValue().get("lockkey"))) {
                System.out.println(uuid + "==========++++++++++++++++++==========");
                stringRedisTemplate.delete(lockkey);
            }
        }
        return count + "++++++++++" + port;
    }
}
