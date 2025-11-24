package kr.myStudent.user.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.user.dto.request.LoginRequest;
import kr.myStudent.user.dto.request.SignUpRequest;
import kr.myStudent.user.dto.request.UserUpdateRequest;
import kr.myStudent.user.dto.response.LoginResponse;
import kr.myStudent.user.dto.response.SignUpResponse;
import kr.myStudent.user.dto.response.UserResponse;
import kr.myStudent.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /** 단건 조회 */
    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<UserResponse>> getOne(@PathVariable String userId) {
        UserResponse result = userService.getOne(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 수정 */
    @PutMapping("/{userId}")
    public ResponseEntity<CommonResponse<UserResponse>> update(
            @PathVariable String userId,
            @RequestBody UserUpdateRequest req
    ) {
        UserResponse result = userService.update(userId, req);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 삭제(비활성화) — status = 2 */
    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponse<String>> deactivate(@PathVariable String userId) {
        userService.deactivate(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, "유저 비활성화 완료"));
    }
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<String>> logout(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("UserId") String userId
    ) {
        String accessToken = authHeader.substring(7);

        userService.logout(accessToken, userId);

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, "로그아웃 완료"));
    }
}
