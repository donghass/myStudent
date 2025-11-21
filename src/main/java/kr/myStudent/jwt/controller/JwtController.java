package kr.myStudent.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.jwt.dto.response.TokenResponse;
import kr.myStudent.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<TokenResponse>> refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("Refresh-Token");
        TokenResponse tokens = jwtService.refreshTokens(refreshToken);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, tokens)
        );
    }
}
