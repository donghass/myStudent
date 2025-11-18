package kr.myStudent.service.user;

import kr.myStudent.domain.jwt.RefreshTokenEntity;
import kr.myStudent.domain.jwt.RefreshTokenRepository;
import kr.myStudent.domain.user.UserEntity;
import kr.myStudent.domain.user.UserRepository;
import kr.myStudent.dto.request.LoginRequest;
import kr.myStudent.dto.request.SignUpRequest;
import kr.myStudent.dto.response.LoginResponse;
import kr.myStudent.dto.response.SignUpResponse;
import kr.myStudent.dto.response.TokenResponse;
import kr.myStudent.jwt.JwtUtil;
import kr.myStudent.mapper.user.UserMapper;
import kr.myStudent.service.jwt.JwtService;
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
    private final JwtService jwtService;

    public SignUpResponse signup(SignUpRequest request) {

        // 이미 존재하는 아이디인지 확인
        if (userRepository.findById(request.getUserId()).isPresent()) {
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
                .userId(user.getUserId())
                .tel(user.getTel())
                .name(user.getName())
                .message("회원가입 성공")
                .build();
    }

    public LoginResponse login(LoginRequest req) {

        UserEntity user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        TokenResponse tokens = jwtService.createTokens(user);


        return LoginResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .userId(user.getUserId())
                .role(user.getRole())
                .message("로그인 성공")
                .build();
    }
}
