package kr.myStudent.mapper;

import kr.myStudent.domain.UserEntity;
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
