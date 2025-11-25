package kr.myStudent.score.domain;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {
    ScoreEntity save(ScoreEntity score);

    Optional<ScoreEntity> findById(Long id);

    void deleteById(Long id);

    List<ScoreEntity> findByUserId(String userId);

    List<ScoreEntity> findByUserIdAndStudentId(String userId, Long studentId);
}
