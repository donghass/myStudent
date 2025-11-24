package kr.myStudent.user.dto.response;

import kr.myStudent.user.domain.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userId;
    private String name;
    private String tel;
    private String status;
    private String role;

    public static UserResponse fromEntity(UserEntity e) {
        return UserResponse.builder()
                .userId(e.getUserId())
                .name(e.getName())
                .tel(e.getTel())
                .status(e.getStatus())
                .role(e.getRole())
                .build();
    }
}
