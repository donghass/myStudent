package kr.myStudent.student.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {
    StudentEntity save(StudentEntity student);

    Optional<StudentEntity> findByStudent(Long studentId, String userId);

    List<StudentEntity> findAllByUserId(String userId);

    void deleteByStudent(Long studentId, String userId);
}
