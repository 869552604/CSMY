package com.ks.model;


import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class ManagerEncrypt {

    private static String encodeRules = "AES/CBC/PKCS5Padding";
    private static String encodeKey = "Shuew237HSFH242s";

    public static String aesDncode(String content,String randomiv) {
        String dncodeContent = null;
        try {
            byte[] decryptBytes = Base64.decode(content,0);
            byte[] keyBytes = encodeKey.getBytes("UTF-8");
            byte[] decryptData = cipherAction(decryptBytes,keyBytes,Cipher.DECRYPT_MODE,randomiv);
            dncodeContent = unzipString(decryptData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dncodeContent;
    }

    public static String unzipString(byte[] bytes) {
        String unzipped = null;
        try {
            byte[] zbytes = bytes;
            byte[] input = new byte[zbytes.length + 1];
            System.arraycopy(zbytes, 0, input, 0, zbytes.length);
            input[zbytes.length] = 0;
            ByteArrayInputStream bin = new ByteArrayInputStream(input);
            InflaterInputStream in = new InflaterInputStream(bin);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = in.read()) != -1) {
                bout.write(b); }
            bout.close();
            unzipped = bout.toString();
        }
        catch (IOException io) {

        }
        return unzipped;
    }

    private static byte[] cipherAction(byte[] contentBytes, byte[] keyBytes, int mode,String iv) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            byte[] initParam = iv.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            Cipher cipher = Cipher.getInstance(encodeRules);
            cipher.init(mode, secretKey, ivParameterSpec);

            return cipher.doFinal(contentBytes);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
