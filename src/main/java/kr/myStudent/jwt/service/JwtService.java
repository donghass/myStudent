package kr.myStudent.jwt.service;

import kr.myStudent.redis.repository.TokenRedisRepository;
import kr.myStudent.user.domain.UserEntity;
import kr.myStudent.user.domain.UserRepository;
import kr.myStudent.jwt.dto.response.TokenResponse;
import kr.myStudent.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRedisRepository redisRepo;

    public TokenResponse createTokens(UserEntity user) {

        String accessToken =
                jwtUtil.generateAccessToken(user.getUserId(), user.getRole());
        String refreshToken =
                jwtUtil.generateRefreshToken(user.getUserId());


        // Redis에 RefreshToken 저장
        redisRepo.saveRefreshToken(
                user.getUserId(),
                refreshToken,
                jwtUtil.getExpiration(refreshToken)
        );
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /** Refresh Token 기반 AccessToken 재발급 */
    public TokenResponse refreshTokens(String refreshToken) {

        // 1) Null 체크
        if (refreshToken == null) {
            throw new IllegalArgumentException("Refresh Token Missing");
        }

        // 2) RefreshToken 자체 유효성 검사 (서명/만료)
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        // 3) refreshToken에서 userId 추출
        String userId = jwtUtil.extractId(refreshToken);

        // 4) Redis 에 저장된 RefreshToken 가져오기
        String saved = redisRepo.getRefreshToken(userId);

        // 5) 존재하지 않음 → 로그아웃된 상태
        if (saved == null) {
            throw new IllegalArgumentException("로그아웃되었습니다. 다시 로그인해주세요.");
        }

        // 6) 전달받은 refreshToken과 Redis RefreshToken 비교
        if (!saved.equals(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token Mismatch");
        }

        // 7) 실제 User 조회 (role 얻기 위해)
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        // 8) 새 Access Token 발급
        String newAccess = jwtUtil.generateAccessToken(user.getUserId(), user.getRole());

        // 9) [선택] Refresh Token Rotation → 새 RefreshToken 발급
        String newRefresh = jwtUtil.generateRefreshToken(user.getUserId());

        // 10) Redis에 새 RefreshToken 저장 (이전 토큰 폐기)
        redisRepo.saveRefreshToken(
                userId,
                newRefresh,
                jwtUtil.getExpiration(newRefresh)
        );

        // 11) 컨트롤러는 AccessToken만 응답하므로 AccessToken만 반환
        return TokenResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .build();
    }

    /** 로그아웃 시 토큰 무효화 */
    public void invalidateTokens(String userId, String accessToken) {

        // ACCESS → 블랙리스트 (남은 TTL 만큼)
        long ttl = jwtUtil.getExpiration(accessToken);
        redisRepo.blacklistAccessToken(accessToken, ttl);

        // REFRESH → Redis 저장된 RefreshToken 제거
        redisRepo.deleteRefreshToken(userId);
    }
}
