package kr.myStudent.redis.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class TokenRedisRepository {

    private final StringRedisTemplate redisTemplate;

    // RefreshToken 저장
    public void saveRefreshToken(String userId, String refreshToken, long ttl) {
        redisTemplate.opsForValue()
                .set("RT:" + userId, refreshToken, ttl, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get("RT:" + userId);
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete("RT:" + userId);
    }

    // AccessToken 블랙리스트 처리
    public void blacklistAccessToken(String accessToken, long ttl) {
        redisTemplate.opsForValue()
                .set("BL:" + accessToken, "logout", ttl, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String accessToken) {
        return redisTemplate.opsForValue().get("BL:" + accessToken) != null;
    }
}
