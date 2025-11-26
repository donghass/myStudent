package kr.myStudent.attendance.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByScheduleId(Long scheduleId);

    List<AttendanceEntity> findByStudentIdAndUserId(Long studentId, String userId);

    Optional<AttendanceEntity> findByScheduleIdAndStudentId(Long scheduleId, Long studentId);
}
