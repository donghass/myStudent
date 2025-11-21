package kr.myStudent.user.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String tel;
    private String password; // 변경 시에만 넣기 (null이면 유지)
}
