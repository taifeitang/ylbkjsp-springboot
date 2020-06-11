package com.wdx.testjson.util;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Description:EncrypAES
 * Author:zhaoming
 * Date: 2020/4/30 9:52
 */
public class EncrypAES {
    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher负责完成加密或解密工作
    private Cipher c;

    /**
     * cbc模式加密
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String encryptCBC(String sSrc, String sKey,String iv) {
        //支持 PKCS7Padding填充，不过这里用不到
//    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    	try{
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] raw = Base64.getDecoder().decode(sKey.getBytes("utf-8"));
            // 判断Key是否为16位

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivSpec);

            byte[] textBytes = sSrc.getBytes("utf-8");
            byte[] encrypted = cipher.doFinal(textBytes);

            return Base64.getEncoder().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        }catch(Exception e){
            return "数据加密失败";
//            throw new RRException("数据加密失败");
        }
    }
    /**
     * 使用参数中的密钥解密
     * @param sSrc 密文
     * @param sKey 密钥
     * @return 明文
     */
    public static String decryptCBC(String sSrc, String sKey,String iv) {
        //支持 PKCS7Padding填充
//    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            // 判断Key是否正确
            if (sKey == null) {
                return null;
            }
            byte[] raw = Base64.getDecoder().decode(sKey);
            // 判断Key是否为16位

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//AES/CBC/PKCS7Padding
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivSpec);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
//                byte[] realOriginal = new byte[original.length - 16];
//                //截去前16个字节
//                System.arraycopy(original, 15, realOriginal, 0, realOriginal.length);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
//                throw new RRException("数据解密失败");
                return "数据解密失败";
            }
        } catch (Exception ex) {
            return null;
        }
    }

    

    
    
    
    
}




