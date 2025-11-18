package kr.myStudent.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignUpRequest {
    private String userId;
    private String password;
    private String name;
    private String tel;
}
