package kr.myStudent.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kr.myStudent.domain.QUserEntity.userEntity;

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
