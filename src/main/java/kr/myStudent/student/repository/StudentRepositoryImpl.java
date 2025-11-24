package kr.myStudent.student.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.student.domain.QStudentEntity;
import kr.myStudent.student.domain.StudentEntity;
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
    public Optional<StudentEntity> findByStudent(Long studentId, String userId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(student)
                        .where(
                                student.studentId.eq(studentId)
                                        .and(student.userId.eq(userId))
                        )
                        .fetchOne()
        );
    }

    @Override
    public List<StudentEntity> findAllByUserId(String userId) {
        return queryFactory
                .selectFrom(student)
                .where(student.userId.eq(userId))
                .fetch();
    }

    @Override
    public void deleteByStudent(Long studentId, String userId) {
        queryFactory
                .delete(student)
                .where(
                        student.studentId.eq(studentId),
                        student.userId.eq(userId)
                )
                .execute();
    }
}
