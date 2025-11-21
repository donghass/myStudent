package kr.myStudent.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "ThisIsAReallyLongSecretKeyThatIsAtLeast32Bytes!!";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private final long ACCESS_TOKEN_TIME = 1000 * 60 * 30;  // 30분
    private final long REFRESH_TOKEN_TIME = 1000 * 60 * 60 * 24 * 14; // 2주

    // 토큰 생성
    public String generateAccessToken(String id, String role) {
        return Jwts.builder()
                .setSubject(id) // 이메일이 아니라 Id
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 사용자 ID 추출
    public String extractId(String token) {
        return parseClaims(token).getSubject();
    }
    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // Claims 파싱
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public long getExpiration(String token) {
        Date exp = parseClaims(token).getExpiration();
        return exp.getTime() - System.currentTimeMillis();
    }


    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            return false;
        }
    }
}
