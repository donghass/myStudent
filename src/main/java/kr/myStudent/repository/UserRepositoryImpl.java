package kr.myStudent.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.domain.UserEntity;
import kr.myStudent.domain.UserRepository;
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
    public Optional<UserEntity> findById(String id) {
        UserEntity user = queryFactory
                .selectFrom(userEntity)
                .where(userEntity.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(user);
    }
}
