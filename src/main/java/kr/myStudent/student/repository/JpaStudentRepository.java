package kr.myStudent.student.repository;

import kr.myStudent.student.domain.StudentEntity;
import kr.myStudent.student.domain.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends JpaRepository<StudentEntity, StudentId> {
}
