package kr.myStudent.score.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.score.domain.QScoreEntity;
import kr.myStudent.score.domain.ScoreEntity;
import kr.myStudent.score.domain.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository {

    private final JpaScoreRepository jpaScoreRepository;
    private final JPAQueryFactory queryFactory;
    QScoreEntity score = QScoreEntity.scoreEntity;

    @Override
    public ScoreEntity save(ScoreEntity score) {
        return jpaScoreRepository.save(score);
    }

    @Override
    public Optional<ScoreEntity> findById(Long id) {
        return jpaScoreRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaScoreRepository.deleteById(id);
    }

    /** Querydsl: userId 기준 조회 */
    @Override
    public List<ScoreEntity> findByUserId(String userId) {
        return queryFactory
                .selectFrom(score)
                .where(score.userId.eq(userId))
                .orderBy(score.testDate.desc())   // optional: 시험 최신순 정렬
                .fetch();
    }

    /** Querydsl: userId + studentId 기준 조회 */
    @Override
    public List<ScoreEntity> findByUserIdAndStudentId(String userId, String studentId) {
        return queryFactory
                .selectFrom(score)
                .where(
                        score.userId.eq(userId),
                        score.studentId.eq(studentId)
                )
                .orderBy(score.testDate.desc())   // optional
                .fetch();
    }
}
