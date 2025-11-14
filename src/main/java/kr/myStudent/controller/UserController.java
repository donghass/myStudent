package kr.myStudent.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.dto.request.LoginRequest;
import kr.myStudent.dto.request.SignUpRequest;
import kr.myStudent.dto.response.LoginResponse;
import kr.myStudent.dto.response.SignUpResponse;
import kr.myStudent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignUpResponse>> signup(@RequestBody SignUpRequest request) {
        SignUpResponse signUpResponse = userService.signup(request);
        CommonResponse<SignUpResponse> response = CommonResponse.success(ResponseCode.SUCCESS, signUpResponse);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.success(ResponseCode.SUCCESS, loginResponse);
        return ResponseEntity.ok(response);
    }
}
