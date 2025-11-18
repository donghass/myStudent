package kr.myStudent.mapper.user;

import kr.myStudent.domain.user.UserEntity;
import kr.myStudent.dto.request.SignUpRequest;

public class UserMapper {

    public static UserEntity toEntity(SignUpRequest request, String encodedPassword) {
        return UserEntity.builder()
                .userId(request.getUserId())
                .password(encodedPassword)
                .name(request.getName())
                .tel(request.getTel())
                .build();
    }
}
