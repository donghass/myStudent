package kr.myStudent.jwt.service;

import kr.myStudent.jwt.domain.RefreshTokenEntity;
import kr.myStudent.jwt.domain.RefreshTokenRepository;
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public TokenResponse createTokens(UserEntity user) {

        String accessToken =
                jwtUtil.generateAccessToken(user.getUserId(), user.getRole());
        String refreshToken =
                jwtUtil.generateRefreshToken(user.getUserId());

        // DB 저장
        refreshTokenRepository.save(
                RefreshTokenEntity.tokenUpdate(user.getUserId(), refreshToken)
        );

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String refreshAccessToken(String refreshToken) {
        // 1) 토큰 null 체크
        if (refreshToken == null) {
            throw new IllegalArgumentException("Refresh Token Missing");
        }

        // 2) 토큰 유효성 검증
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        // 3) userId 추출
        String userId = jwtUtil.extractId(refreshToken);

        // 4) DB 사용자 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        // 5) 새 Access Token 생성
        return jwtUtil.generateAccessToken(user.getUserId(), user.getRole());
    }
}
