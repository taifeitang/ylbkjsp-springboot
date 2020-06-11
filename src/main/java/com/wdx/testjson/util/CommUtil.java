package com.wdx.testjson.util;


import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommUtil {



    public static JSONObject get_post_josn(JSONObject json, String get_url){
        String iv = "bURwd0AwZUM1ciVvXkR6Mw==";
        String sKey = "MChFY2dzdEIheU1MaU4zL3Z6TTEoQGIjVDU0JDNSciU=";
        JSONObject result = new JSONObject();
        try{
            JSONObject one_obj = new JSONObject();
            one_obj.put("data",EncrypAES.encryptCBC(json.toString(),sKey,iv));
            one_obj.put("appid","213330428");
            one_obj.put("iv","bURwd0AwZUM1ciVvXkR6Mw==");
            String   result_str = post(one_obj,get_url);
            System.out.print("result_str:"+result_str);
            if(result_str.contains("{")&&result_str.contains("}")){
                //拿到传递JSONObject
                result = JSONObject.fromObject(result_str);
                if(result.containsKey("errcode")){

                    if(result.getInt("errcode")==0){
                        //成功
                        if(result.containsKey("data")){
                            result.put("data", EncrypAES.decryptCBC(result.getString("data"),sKey,result.getString("iv")));
                        }
                    }

                }

            }
        } catch (Exception e){
            System.out.println("请求异常");
        } finally{
        }

        System.out.println("接口："+get_url+",返回结果："+result);
        return result;
    }

    public static String post(JSONObject json, String url){
        String result = "";
        HttpPost post = new HttpPost("https://minipro.phhp.com.cn/HospitalThirdPartyApi/"+url);
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();

            post.setHeader("Content-Type","application/json");
            StringEntity postingString = new StringEntity(json.toString(),"utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);

            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber= new StringBuilder();
            String line = null;
            while((line = br.readLine())!=null){
                strber.append(line+'\n');
            }
            br.close();
            in.close();
            result = strber.toString();
            if(response.getStatusLine().getStatusCode()!=HttpStatus.OK.value()){
                result = "服务器异常";
            }
        } catch (Exception e){
            System.out.println("请求异常");
            System.out.println(e);
        } finally{
            post.abort();
        }
        return result;
    }
}
