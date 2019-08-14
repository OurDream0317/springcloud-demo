package com.lechi.springcloudconfigclient.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPramas {
    @Value("${age}")
    private int age;
    @Value("${name}")
    private String name;

    @RequestMapping("/get")
    public String method() {
        return "我叫" + name + ", 我的年紀是" + age;
    }
}
