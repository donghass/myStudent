package kr.myStudent.user.mapper;

import kr.myStudent.user.domain.UserEntity;
import kr.myStudent.user.dto.request.SignUpRequest;

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
