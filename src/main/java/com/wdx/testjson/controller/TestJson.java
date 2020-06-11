package com.wdx.testjson.controller;


import com.wdx.testjson.pojo.User;
import com.wdx.testjson.util.CommUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestJson {
    @RequestMapping("/getJson")
    public User getJson(){
        User u=new User();
        u.setName("wdx");
        u.setPassword("123");
        return u;
    }
    @RequestMapping("/hospital_prepay/listPreVisit")
    public JSONObject get(){
        JSONObject reqObj = new JSONObject();
        //reqObj.put("DayStart","2018-05-01");
       // reqObj.put("DayEnd","2018-05-01");
       // reqObj.put("RegisterNo","S3249302");
        reqObj.put("PageNo","1");
        reqObj.put("PageSize","3");
        reqObj.put("PatientId","8779204");//11230607 8779204
        System.out.println(reqObj);
        //JSONObject result=CommUtil.get_post_josn(reqObj,"outpatient/getNoPayList");
        JSONObject result=CommUtil.get_post_josn(reqObj,"hospital_prepay/listPreVisit");

        return  result;
    }
    @RequestMapping("/outpatient/getNoPayList")
    public JSONObject getNoPayList(){
        JSONObject reqObj = new JSONObject();
        reqObj.put("DayStart","2018-05-01");
         reqObj.put("DayEnd","2019-05-01");
         reqObj.put("RegisterNo","S3249302");
        reqObj.put("PatientId","8779204");//11230607 8779204
        //String post_str="{\"body\":{\"channel\":\""+channel+"\",\"payOrderId\":\""+payOrderId+"\"}}";//测试数据构造（前端传入list）
        //String _str="{\"body\":[{\"channel\":\""+channel+"\",\"payOrderId\":\""+payOrderId+"\"},{\"channel\":\"+channel+\",\"payOrderId\":\"+payOrderId+\"\"}]}";
        System.out.println(reqObj);
        JSONObject result=CommUtil.get_post_josn(reqObj,"outpatient/getNoPayList");
        return  result;
    }
@RequestMapping("outpatient/getOutpatientPay")
    public  JSONObject getOutpatientPay(){
    JSONObject reqObj = new JSONObject();
    String uniqueId = System.currentTimeMillis()+ UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    reqObj.put("CardNo","511602198402230320");//身份证号
    reqObj.put("PayNo","mzjf202006040515402865");//银联交易流水号
    reqObj.put("TerminalNo","oDnlc5ULmKRYz6GHAc3RAbU0DPP0");//终端号
    reqObj.put("PatientId","8779204");//11230607 8779204 病人ID
    reqObj.put("CardTypeId","511");//身份证类型固定为511
    reqObj.put("SysRefNo",uniqueId);//账单id，使用全局唯一主键生成策略，
    reqObj.put("InsureType","0");//结算类型
    reqObj.put("UserId","0");//我们自己的用户id
    reqObj.put("PayMode","813");//支付模式
    reqObj.put("PayType","3");//支付类型
    reqObj.put("RegisterNo","S5875597");//挂号单号
    reqObj.put("PatientPayMoney","167.51");//由挂号单号（RegisterNo）查询门诊列表待缴接口获取用户需要缴费的金额
    //reqObj.put("OpCode","3");//InsureType为1时才是必传项
   // String list="{\"Item\":[{\"ReceiptNo\":\""+"TA683803"+"\",\"PayAmt\":\""+"162.2"+"\"},{\"ReceiptNo\":\"TA683804\",\"PayAmt\":\"5.31\"}]}";
    String list="[{\"ReceiptNo\":\""+"TA683803"+"\",\"PayAmt\":\""+"162.2"+"\"},{\"ReceiptNo\":\"TA683804\",\"PayAmt\":\"5.31\"}]";
    reqObj.put("List",list);
    System.out.println(reqObj);
    JSONObject result=CommUtil.get_post_josn(reqObj,"outpatient/getOutpatientPay");
    return  result;






    }



}
