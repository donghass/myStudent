package kr.myStudent.repository;

import kr.myStudent.domain.RefreshTokenEntity;
import kr.myStudent.domain.RefreshTokenRepository;
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
