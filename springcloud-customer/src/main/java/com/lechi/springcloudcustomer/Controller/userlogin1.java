package com.lechi.springcloudcustomer.Controller;


import com.lechi.springcloudcustomer.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userlogin1 {
    @Autowired
    private userService userService;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return userService.home(name);
    }

    @GetMapping(value = "/userlogin")
    public String userLogin(String username, String pwd) {
        String str = userService.userLogin(username, pwd);
        if (str.equals("success")) {
            System.out.println(username + "登录成功！");
            return "index";
        }
        System.out.println(username + "登录失败，请重新登录！");
        return "login";
    }
}
