package kr.myStudent.progress.domain;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository {

    ProgressEntity save(ProgressEntity entity);

    Optional<ProgressEntity> findById(Long id);

    void deleteById(Long id);

    List<ProgressEntity> findLatestProgressByUserId(String userId);

    List<ProgressEntity> findByStudent(String userId, Long studentId);
}
