package kr.myStudent.student.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import kr.myStudent.student.domain.QStudentSubjectEntity;
import kr.myStudent.student.domain.StudentSubjectEntity;
import kr.myStudent.student.domain.StudentSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentSubjectRepositoryImpl implements StudentSubjectRepository {
    private final JpaStudentSubjectRepository jpaStudentSubjectRepository;
    private final JPAQueryFactory queryFactory;
    QStudentSubjectEntity studentSubjectEntity = QStudentSubjectEntity.studentSubjectEntity;

    @Override
    public StudentSubjectEntity save(StudentSubjectEntity studentSubject) {
        return jpaStudentSubjectRepository.save(studentSubject);
    }

    /** 특정 학생의 과목 전체 조회 */
    @Override
    public List<StudentSubjectEntity> findByStudentId(Long studentId, String userId) {
        return queryFactory
                .selectFrom(studentSubjectEntity)
                .where(
                        studentSubjectEntity.studentId.eq(studentId),
                        studentSubjectEntity.userId.eq(userId)
                )
                .fetch();
    }

    /** PK로 삭제 */
    @Override
    @Transactional
    public void deleteByIdAndStudentId(Long studentSubjectId) {
        queryFactory
                .delete(studentSubjectEntity)
                .where(studentSubjectEntity.studentSubjectId.eq(studentSubjectId))
                .execute();
    }

    /** PK로 단건 조회 */
    @Override
    public Optional<StudentSubjectEntity> findById(Long studentSubjectId) {
        StudentSubjectEntity result = queryFactory
                .selectFrom(studentSubjectEntity)
                .where(studentSubjectEntity.studentSubjectId.eq(studentSubjectId))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
