package com.xys.server;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Method;
import java.security.SecureRandom;

public class AESEncryptTools {
    //加密算是是AES
    private static final String ALGORITHM = "AES";
    //转换格式
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    //利用16个字节128位的key给src加密
    @SuppressWarnings("unused")
    public static byte[] encrypt(byte[] src,byte[]key)
    {
        try {
            //加密
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key,ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,new SecureRandom());
            byte[] enMsgBytes = cipher.doFinal(src);
            return enMsgBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }


    //利用16个字节128位的key给src解密
    @SuppressWarnings("unused")
    public static byte[] decrypt(byte[] encryptBytes,byte[]key){
        try {
            //解密
            Cipher deCipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key,ALGORITHM);
            deCipher.init(Cipher.DECRYPT_MODE, keySpec,new SecureRandom());
            byte[] deMsgBytes = deCipher.doFinal(encryptBytes);
            return deMsgBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String key = "terabitsterabits";
    public static void main(String[] args) throws Exception{
        String msg = "AA1A08666460310823010033";
        System.out.println("加密前："+msg);
        byte[] encryptBytes = AESEncryptTools.encrypt(msg.getBytes(),key.getBytes());
        System.out.println("加密后："+encodeBase64(encryptBytes
        ));
        /*byte[] deMsgBytes = AESEncryptTools.decrypt(encryptBytes,key.getBytes());
        System.out.println("解密后："+new String(deMsgBytes));*/
    }
}
