package com.lechi.nginx1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class nginxDemo {
    @RequestMapping("/test")
    public String login() {
        return "login";
    }
}
