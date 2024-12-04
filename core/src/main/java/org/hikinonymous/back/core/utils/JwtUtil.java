package org.hikinonymous.back.core.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerErrorException;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil {

    private static String secretKey = "HIKINONYMOUS_SECRET_KEY";

    private static final long TOKEN_VALID_MILLISECOND = 1000L * 60 * 60 * 24 * 7; // 7일

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * JWT 생성
     */
    public static String makeJwt(long seq) {
        return createToken("1" + convertSerializable(seq));
    }

    /**
     * 직렬화
     * Base64 인코딩
     */
    public static String convertSerializable(long seq) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
        byteBuffer.putLong(seq);
        return Base64.getEncoder().encodeToString(byteBuffer.array());
    }

    /**
     * JWT 생성
     */
    public static String createToken(String key) {
        Date date = new Date();
        return Jwts.builder()
                .setClaims(Jwts.claims().setId(key))
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + TOKEN_VALID_MILLISECOND))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * JWT 복호화
     */
    public static long decJwt(String jwt) {
        long pk = 0L;
        Jws<Claims> claimsJwt = getClaims(jwt);
        if (!Objects.isNull(claimsJwt) && validateToken(claimsJwt)) {
            String id = getId(claimsJwt);
            pk = convertData(id.substring(1));
        }
        return pk;
    }

    /**
     * JWT 복호화
     */
    public static Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt);
        } catch (SignatureException e) {
            throw new ServerErrorException("유효하지 않는 JWT 토큰입니다.", null);
        }
    }

    /**
     * JWT 시간 체크
     */
    public static boolean validateToken(Jws<Claims> claims) {
        return !claims.getBody()
                .getExpiration()
                .before(new Date());
    }

    /**
     * JWT GET ID
     */
    public static String getId(Jws<Claims> claims) {
        return claims.getBody().getId();
    }

    /**
     * Base64 디코딩
     * 역직렬화
     */
    public static long convertData(String code) {
        byte[] bytes = Base64.getDecoder().decode(code);
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return byteBuffer.getLong();
    }

    /**
     * 토큰 valid
     */
    public boolean isTokenValid(String token) {
        try {
            decJwt(token);
            return true;
        } catch (SignatureException e) {
            logger.error("잘못된 JWT 서명입니다.");

        } catch (MalformedJwtException e) {
            logger.error("잘못된 JWT 토큰입니다.");

        } catch (ExpiredJwtException e) {
            logger.error("만료된 JWT 토큰입니다.");

        } catch (UnsupportedJwtException e) {
            logger.error("지원되지 않는 JWT 토큰입니다.");

        } catch (IllegalArgumentException e) {
            logger.error("유효하지 않는 JWT 토큰입니다.");
        }
        return false;
    }

    public long getPkFromToken(String token) {
        return decJwt(token);
    }
}
