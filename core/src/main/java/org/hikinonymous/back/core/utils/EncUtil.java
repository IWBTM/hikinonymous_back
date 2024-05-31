package org.hikinonymous.back.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncUtil {


    private static final String PRIVATE_KEY = "HIKINONYMOUS_PRIVATE_KEY";

    private static final String AES_ALGORITHMS = "AES/CBC/PKCS5Padding";

    private static final String IV = "4VD6SHIOL8KWJER5";

    /**
     * AES256 암호화
     */
    public static String encryptAES256(String text) {
        String enc;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHMS);
            SecretKeySpec keySpec = new SecretKeySpec(PRIVATE_KEY.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return enc = Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES256 복호화
     */
    public static String decryptAES256(String enc) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHMS);
            SecretKeySpec keySpec = new SecretKeySpec(PRIVATE_KEY.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            byte[] decodedBytes = Base64.getDecoder().decode(enc);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
