package kr.myStudent.jwt.repository;

import kr.myStudent.jwt.domain.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
}
