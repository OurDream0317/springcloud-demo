package com.lechi.nginx.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class nginxDemo {
    @RequestMapping("/test")
    public String login() {
        return "login";
    }
}
