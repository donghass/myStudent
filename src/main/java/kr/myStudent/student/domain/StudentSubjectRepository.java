package kr.myStudent.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentSubjectRepository{

    StudentSubjectEntity save(StudentSubjectEntity studentSubject);

    List<StudentSubjectEntity> findByStudentId(Long studentId, String userId);

    void deleteByIdAndStudentId(Long studentSubjectId);

    Optional<StudentSubjectEntity> findById(Long studentSubjectId);
}