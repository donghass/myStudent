package kr.myStudent.score.repository;

import kr.myStudent.score.domain.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaScoreRepository extends JpaRepository<ScoreEntity,Long> {
}
