package kr.myStudent.service;

import kr.myStudent.domain.RefreshTokenEntity;
import kr.myStudent.domain.RefreshTokenRepository;
import kr.myStudent.domain.UserEntity;
import kr.myStudent.domain.UserRepository;
import kr.myStudent.dto.request.LoginRequest;
import kr.myStudent.dto.request.SignUpRequest;
import kr.myStudent.dto.response.LoginResponse;
import kr.myStudent.dto.response.SignUpResponse;
import kr.myStudent.jwt.JwtUtil;
import kr.myStudent.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignUpResponse signup(SignUpRequest request) {

        // 이미 존재하는 아이디인지 확인
        if (userRepository.findById(request.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // DTO → Entity
        UserEntity user = UserMapper.toEntity(request, encodedPassword);

        // DB 저장
        userRepository.save(user);

        // 응답 DTO 반환
        return SignUpResponse.builder()
                .id(user.getId())
                .tel(user.getTel())
                .name(user.getName())
                .message("회원가입 성공")
                .build();
    }

    public LoginResponse login(LoginRequest req) {

        UserEntity user = userRepository.findById(req.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());

        // DB 저장
        refreshTokenRepository.save(RefreshTokenEntity.tokenUpdate(user.getId(),refreshToken));

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .role(user.getRole())
                .message("로그인 성공")
                .build();
    }
}
