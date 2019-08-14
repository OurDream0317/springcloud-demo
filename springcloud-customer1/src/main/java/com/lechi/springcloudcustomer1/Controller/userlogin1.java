package com.lechi.springcloudcustomer1.Controller;


import com.alibaba.fastjson.JSONObject;
import com.lechi.springcloudcustomer1.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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


    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, MultipartFile file) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");

        String res = sdf.format(new Date());

//服务器上使用

        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/image";


//本地使用

        /*String rootPath=  request.getSession().getServletContext().getRealPath("/static/image");*/

//原始名称

        String originalFilename = file.getOriginalFilename();

//新的文件名称

        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));

//新文件

        File newFile = new File(rootPath + File.separator + newFileName);

        System.out.println(newFile);

//将内存中的数据写入磁盘

        file.transferTo(newFile);

//完整的url

        String fileUrl = "/image/" + newFileName;

        Map map = new HashMap();

        Map map2 = new HashMap();

        map.put("code", 0);//0表示成功，1失败

        map.put("msg", "上传成功");//提示消息

        map.put("data", map2);

        map2.put("src", fileUrl);//图片url

        map2.put("title", newFileName);//图片名称，这个会显示在输入框里

        String result = new JSONObject(map).toString();

        return result;

    }
}
