package kr.myStudent.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String id;
    private String role;
    private String message;
}
