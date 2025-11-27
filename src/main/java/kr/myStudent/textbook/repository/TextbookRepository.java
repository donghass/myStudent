package kr.myStudent.textbook.repository;

import kr.myStudent.textbook.domain.TextbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextbookRepository extends JpaRepository<TextbookEntity, Long> {
    List<TextbookEntity> findByStudentIdAndUserId(Long studentId, String userId);
}
