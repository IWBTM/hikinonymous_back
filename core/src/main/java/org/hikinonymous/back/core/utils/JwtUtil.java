package org.hikinonymous.back.core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static String secretKey = "HIKINONYMOUS_SECRET_KEY";

    private static long tokenValidMillisecond = 1000L * 60 * 60 * 24 * 7; // 7일

    public static String makeJwt(long seq) {
        return createToken("1" + convertSerializable(seq));
    }

    public static String convertSerializable(long seq) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeLong(seq);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createToken(String key) {
        Date date = new Date();
        return Jwts
                .builder()
                .setClaims(Jwts.claims().setId(key))
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
