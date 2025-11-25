package kr.myStudent.schedule.service;

import kr.myStudent.schedule.domain.ScheduleEntity;
import kr.myStudent.schedule.domain.ScheduleRepository;
import kr.myStudent.schedule.dto.request.ScheduleCreateRequest;
import kr.myStudent.schedule.dto.request.ScheduleUpdateRequest;
import kr.myStudent.schedule.dto.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    /** 등록 */
    public ScheduleResponse create(ScheduleCreateRequest req) {

        ScheduleEntity entity = ScheduleEntity.builder()
                .subject(req.getSubject())
                .studentId(req.getStudentId())
                .userId(req.getUserId())
                .classDate(req.getClassDate())
                .classLocation(req.getClassLocation())
                .content(req.getContent())
                .build();

        scheduleRepository.save(entity);

        return ScheduleResponse.fromEntity(entity);
    }

    /** 단건 조회 */
    public ScheduleResponse getOne(Long id) {

        ScheduleEntity entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스케줄을 찾을 수 없습니다."));

        return ScheduleResponse.fromEntity(entity);
    }

    /** userId 전체 조회 */
    public List<ScheduleResponse> getByUserId(String userId) {

        return scheduleRepository.findByUserId(userId).stream()
                .map(ScheduleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** userId + studentId 조회 */
    public List<ScheduleResponse> getByUserIdAndStudentId(String userId, Long studentId) {

        return scheduleRepository.findByUserIdAndStudentId(userId, studentId).stream()
                .map(ScheduleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 수정 */
    public ScheduleResponse update(Long id, ScheduleUpdateRequest req) {

        ScheduleEntity entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스케줄을 찾을 수 없습니다."));

        entity.updateSchedule(req);

        scheduleRepository.save(entity);

        return ScheduleResponse.fromEntity(entity);
    }

    /** 삭제 */
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    /** 날짜 범위 조회 */
    public List<ScheduleResponse> getDateRange(String userId, LocalDateTime start, LocalDateTime end) {

        return scheduleRepository.findByUserIdAndDateRange(userId, start, end).stream()
                .map(ScheduleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 오늘 수업 조회 */
    public List<ScheduleResponse> getTodaySchedules(String userId) {

        return scheduleRepository.findTodaySchedules(userId).stream()
                .map(ScheduleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 월간 수업 조회 (달력 API) */
    public List<ScheduleResponse> getMonthlySchedules(String userId, int year, int month) {

        return scheduleRepository.findMonthlySchedules(userId, year, month).stream()
                .map(ScheduleResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
