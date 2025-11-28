package kr.myStudent.homework.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    List<HomeworkEntity> findByProgressId(Long progressId);

    List<HomeworkEntity> findByStudentIdAndUserId(Long studentId, String userId);

    List<HomeworkEntity> findByTextbookId(Long textbookId);

    Optional<HomeworkEntity> findByHomeworkIdAndUserId(Long homeworkId, String userId);
}

