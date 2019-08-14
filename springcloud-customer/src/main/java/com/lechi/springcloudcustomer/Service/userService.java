package com.lechi.springcloudcustomer.Service;

import com.lechi.springcloudcustomer.Controller.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "springcloud-privater", fallback = SchedualServiceHiHystric.class)
public interface userService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String home(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/userlogin", method = RequestMethod.GET)
    String userLogin(@RequestParam("username") String username, @RequestParam("pwd") String pwd);
}
