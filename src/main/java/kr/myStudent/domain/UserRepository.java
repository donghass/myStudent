package kr.myStudent.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserEntity> findById(String id);

    UserEntity save(UserEntity user);
}
