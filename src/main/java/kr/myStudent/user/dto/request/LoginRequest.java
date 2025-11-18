package kr.myStudent.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String userId;
    private String password;
}
