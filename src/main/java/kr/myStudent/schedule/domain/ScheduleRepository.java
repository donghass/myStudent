package kr.myStudent.schedule.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleEntity save(ScheduleEntity schedule);

    Optional<ScheduleEntity> findById(Long id);

    void deleteById(Long id);

    List<ScheduleEntity> findByUserId(String userId);

    List<ScheduleEntity> findByUserIdAndStudentId(String userId, Long studentId);

    List<ScheduleEntity> findByUserIdAndDateRange(String userId, LocalDateTime start, LocalDateTime end);

    List<ScheduleEntity> findTodaySchedules(String userId);

    List<ScheduleEntity> findMonthlySchedules(String userId, int year, int month);
}
