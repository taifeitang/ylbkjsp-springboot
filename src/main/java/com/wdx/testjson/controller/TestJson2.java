package com.wdx.testjson.controller;

import com.wdx.testjson.pojo.User;
import net.sf.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestJson2 {
    @ResponseBody
    @RequestMapping("/getUser")
    public JSONObject getUser() {
        User u = new User();
        u.setName("王");
        u.setPassword("123");
        JSONObject object = JSONObject.fromObject(u);
        return object;
    }
}
