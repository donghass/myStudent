package kr.myStudent.user.service;

import kr.myStudent.jwt.domain.RefreshTokenRepository;
import kr.myStudent.user.domain.UserEntity;
import kr.myStudent.user.domain.UserRepository;
import kr.myStudent.user.dto.request.LoginRequest;
import kr.myStudent.user.dto.request.SignUpRequest;
import kr.myStudent.user.dto.request.UserUpdateRequest;
import kr.myStudent.user.dto.response.LoginResponse;
import kr.myStudent.user.dto.response.SignUpResponse;
import kr.myStudent.jwt.dto.response.TokenResponse;
import kr.myStudent.jwt.JwtUtil;
import kr.myStudent.user.dto.response.UserResponse;
import kr.myStudent.user.mapper.UserMapper;
import kr.myStudent.jwt.service.JwtService;
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

    /** 유저 정보 조회 */
    public UserResponse getOne(String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return UserResponse.fromEntity(user);
    }

    /** 유저 정보 수정 */
    public UserResponse update(String userId, UserUpdateRequest req) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        String encodedPassword = null;
        if (req.getPassword() != null) {
            encodedPassword = passwordEncoder.encode(req.getPassword());
        }

        user.updateInfo(
                req.getName(),
                req.getTel(),
                encodedPassword
        );

        userRepository.save(user);

        return UserResponse.fromEntity(user);
    }

    /** 유저 삭제(비활성화) */
    public void deactivate(String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.deactivate();

        userRepository.save(user);
    }
}
