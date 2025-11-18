package kr.myStudent.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.user.domain.UserEntity;
import kr.myStudent.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kr.myStudent.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(String userId) {
        UserEntity user = queryFactory
                .selectFrom(userEntity)
                .where(userEntity.userId.eq(userId))
                .fetchOne();

        return Optional.ofNullable(user);
    }
}
