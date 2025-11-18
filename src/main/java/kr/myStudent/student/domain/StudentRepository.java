package kr.myStudent.student.domain;


import com.querydsl.core.Fetchable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {
    StudentEntity save(StudentEntity student);

    Optional<StudentEntity> findById(StudentId id);

    List<StudentEntity> findAllByUserId(String userId);

    void deleteById(StudentId id);
}
