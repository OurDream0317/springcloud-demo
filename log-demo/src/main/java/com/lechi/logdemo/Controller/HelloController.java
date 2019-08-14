package com.lechi.logdemo.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
     /*   if(true){
            int[] a=new int[3];
            try {
                for (int i=0;i<=4;i++){
                    a[i]=i;
                }
            }catch (Exception e){
                throw e;
            }

        }*/
        int i = 10 / 0;
        return "Hello " + name;
    }

}
