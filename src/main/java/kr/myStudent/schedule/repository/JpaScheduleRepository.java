package kr.myStudent.schedule.repository;

import kr.myStudent.schedule.domain.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaScheduleRepository extends JpaRepository<ScheduleEntity,Long> {
}
