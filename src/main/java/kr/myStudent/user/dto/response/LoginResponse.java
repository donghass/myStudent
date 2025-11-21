package kr.myStudent.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String userId;
    private String name;
    private String role;
    private String message;
}
