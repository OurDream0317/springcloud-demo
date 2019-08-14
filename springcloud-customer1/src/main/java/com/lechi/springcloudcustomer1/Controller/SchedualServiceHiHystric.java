package com.lechi.springcloudcustomer1.Controller;


import com.lechi.springcloudcustomer1.Service.userService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SchedualServiceHiHystric implements userService {
    @Value("${server.port}")
    String port;

    @Override
    public String home(String name) {
        return "sorry " + name + " ,我的端口是" + port;
    }

    @Override
    public String userLogin(String username, String pwd) {
        return null;
    }
}
