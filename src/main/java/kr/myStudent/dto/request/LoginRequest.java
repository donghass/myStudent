package kr.myStudent.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String id;
    private String password;
}
