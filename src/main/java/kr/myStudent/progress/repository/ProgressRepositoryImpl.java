package kr.myStudent.progress.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.progress.domain.ProgressEntity;
import kr.myStudent.progress.domain.ProgressRepository;
import kr.myStudent.progress.domain.QProgressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProgressRepositoryImpl implements ProgressRepository {

    private final JpaProgressRepository jpa;
    private final JPAQueryFactory queryFactory;
    QProgressEntity progress = QProgressEntity.progressEntity;
    QProgressEntity p2 = new QProgressEntity("p2");
    QProgressEntity p3 = new QProgressEntity("p3");

    @Override
    public ProgressEntity save(ProgressEntity entity) {
        return jpa.save(entity);
    }

    @Override
    public Optional<ProgressEntity> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    /** 학생별 가장 최신 진도 1개 */
    @Override
    public List<ProgressEntity> findLatestProgressByUserId(String userId) {
        return queryFactory
                .selectFrom(progress)
                .where(
                        progress.progressId.in(
                                JPAExpressions
                                        .select(p2.progressId)
                                        .from(p2)
                                        .where(
                                                p2.userId.eq(userId),
                                                p2.lessonDate.eq(
                                                        JPAExpressions
                                                                .select(p3.lessonDate.max())
                                                                .from(p3)
                                                                .where(
                                                                        p3.studentId.eq(p2.studentId),
                                                                        p3.userId.eq(userId)
                                                                )
                                                )
                                        )
                        )
                )
                .orderBy(progress.lessonDate.desc())
                .fetch();
    }

    /** 특정 학생의 전체 진도 */
    @Override
    public List<ProgressEntity> findByStudent(String userId, Long studentId) {
        return queryFactory
                .selectFrom(progress)
                .where(
                        progress.userId.eq(userId),
                        progress.studentId.eq(studentId)
                )
                .orderBy(progress.lessonDate.desc())
                .fetch();
    }
}
