package kr.myStudent.schedule.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.myStudent.schedule.domain.QScheduleEntity;
import kr.myStudent.schedule.domain.ScheduleEntity;
import kr.myStudent.schedule.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JpaScheduleRepository jpaScheduleRepository;
    private final JPAQueryFactory queryFactory;

    QScheduleEntity schedule = QScheduleEntity.scheduleEntity;

    @Override
    public ScheduleEntity save(ScheduleEntity schedule) {
        return jpaScheduleRepository.save(schedule);
    }

    @Override
    public Optional<ScheduleEntity> findById(Long id) {
        return jpaScheduleRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaScheduleRepository.deleteById(id);
    }

    /** Querydsl로 userId만 조회 */
    @Override
    public List<ScheduleEntity> findByUserId(String userId) {
        return queryFactory
                .selectFrom(schedule)
                .where(schedule.userId.eq(userId))
                .orderBy(schedule.classDate.desc())
                .fetch();
    }

    /** Querydsl로 userId + studentId 조회 */
    @Override
    public List<ScheduleEntity> findByUserIdAndStudentId(String userId, Long studentId) {
        return queryFactory
                .selectFrom(schedule)
                .where(
                        schedule.userId.eq(userId),
                        schedule.studentId.eq(studentId))
                .orderBy(schedule.classDate.desc())
                .fetch();
    }

    @Override
    public List<ScheduleEntity> findByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return queryFactory
                .selectFrom(schedule)
                .where(
                        schedule.userId.eq(userId),
                        schedule.classDate.between(start, end))
                .orderBy(schedule.classDate.asc())
                .fetch();
    }

    @Override
    public List<ScheduleEntity> findTodaySchedules(String userId) {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return queryFactory
                .selectFrom(schedule)
                .where(
                        schedule.userId.eq(userId),
                        schedule.classDate.between(start, end))
                .orderBy(schedule.classDate.asc())
                .fetch();
    }

    @Override
    public List<ScheduleEntity> findMonthlySchedules(String userId, int year, int month) {

        LocalDate first = LocalDate.of(year, month, 1);
        LocalDate last = first.plusMonths(1); // 다음 달 1일

        LocalDateTime start = first.atStartOfDay();
        LocalDateTime end = last.atStartOfDay();

        return queryFactory
                .selectFrom(schedule)
                .where(
                        schedule.userId.eq(userId),
                        schedule.classDate.between(start, end))
                .orderBy(schedule.classDate.asc())
                .fetch();
    }

}
