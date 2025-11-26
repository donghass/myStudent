package kr.myStudent.progress.repository;

import kr.myStudent.progress.domain.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProgressRepository extends JpaRepository<ProgressEntity, Long> {
}
