package kr.myStudent.student.repository;

import kr.myStudent.student.domain.StudentSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentSubjectRepository extends JpaRepository<StudentSubjectEntity, Long> {
}
