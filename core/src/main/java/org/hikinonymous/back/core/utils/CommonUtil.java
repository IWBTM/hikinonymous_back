package org.hikinonymous.back.core.utils;

import java.security.MessageDigest;

public class CommonUtil {

    /**
     * SHA256 단방향 암호화
     */
    public static String encryptSHA256(String text) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(text.getBytes());
            byte byteData[] = sh.digest();
            for (int i = 0; i < byteData.length; i++) sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
