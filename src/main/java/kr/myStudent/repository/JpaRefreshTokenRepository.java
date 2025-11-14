package kr.myStudent.repository;

import kr.myStudent.domain.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
}
