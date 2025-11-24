package kr.myStudent.student.repository;

import kr.myStudent.student.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {
}
