package kr.myStudent.repository.jwt;

import kr.myStudent.domain.jwt.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    @Override
    public void save(RefreshTokenEntity refreshTokenEntity) {
        jpaRefreshTokenRepository.save(refreshTokenEntity);
    }
}
