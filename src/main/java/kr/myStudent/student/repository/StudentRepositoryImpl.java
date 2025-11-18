package kr.myStudent.student.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.student.domain.QStudentEntity;
import kr.myStudent.student.domain.StudentEntity;
import kr.myStudent.student.domain.StudentId;
import kr.myStudent.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JpaStudentRepository jpaStudentRepository;
    private final JPAQueryFactory queryFactory;
    QStudentEntity student = QStudentEntity.studentEntity;
    
    @Override
    public StudentEntity save(StudentEntity student) {
        return jpaStudentRepository.save(student);
    }

    @Override
    public Optional<StudentEntity> findById(StudentId id) {
        return jpaStudentRepository.findById(id);
    }

    @Override
    public List<StudentEntity> findAllByUserId(String userId) {
        return queryFactory
                .selectFrom(student)
                .where(student.id.userId.eq(userId))
                .fetch();
    }

    @Override
    public void deleteById(StudentId id) {
        jpaStudentRepository.deleteById(id);
    }
}
