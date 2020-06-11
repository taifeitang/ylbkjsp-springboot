package com.wdx.testjson.controller;

import com.wdx.testjson.pojo.User;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestJsonObeject {
    @RequestMapping("/jsonObject")
    public  JSONObject getObject(){
        JSONObject object=new JSONObject();
        object.put("id",1);
        object.put("name","wangtaisheng");
        object.put(2,8);
        System.out.println(object.get("name"));
        System.out.println(object.get(1));
        System.out.println(object.get("2"));
        return object;
    }
    @RequestMapping("/getJson1")
    public JSONObject getO(User u) {
        JSONObject object = JSONObject.fromObject(u);
        object.get("name");
        boolean i=0!=1;
        return object;
    }
}
