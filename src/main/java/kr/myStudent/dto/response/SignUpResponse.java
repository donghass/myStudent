package kr.myStudent.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {
    private String id;
    private String name;
    private String tel;
    private String message;
}
