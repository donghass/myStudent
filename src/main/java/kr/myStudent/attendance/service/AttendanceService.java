package kr.myStudent.attendance.service;

import kr.myStudent.attendance.domain.AttendanceEntity;
import kr.myStudent.attendance.domain.AttendanceRepository;
import kr.myStudent.attendance.dto.request.AttendanceCreateRequest;
import kr.myStudent.attendance.dto.request.AttendanceUpdateRequest;
import kr.myStudent.attendance.dto.response.AttendanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Transactional
    public AttendanceResponse create(AttendanceCreateRequest request) {
        AttendanceEntity attendance = AttendanceEntity.builder()
                .scheduleId(request.getScheduleId())
                .studentId(request.getStudentId())
                .userId(request.getUserId())
                .status(request.getStatus())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .memo(request.getMemo())
                .build();

        attendanceRepository.save(attendance);
        return AttendanceResponse.fromEntity(attendance);
    }

    @Transactional
    public AttendanceResponse update(Long id, AttendanceUpdateRequest request) {
        AttendanceEntity attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("출석 기록을 찾을 수 없습니다."));

        attendance.update(
                request.getStatus(),
                request.getCheckInTime(),
                request.getCheckOutTime(),
                request.getMemo());

        return AttendanceResponse.fromEntity(attendance);
    }

    public AttendanceResponse getById(Long id) {
        AttendanceEntity attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("출석 기록을 찾을 수 없습니다."));
        return AttendanceResponse.fromEntity(attendance);
    }

    public List<AttendanceResponse> getByStudentId(Long studentId, String userId) {
        return attendanceRepository.findByStudentIdAndUserId(studentId, userId)
                .stream()
                .map(AttendanceResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public AttendanceResponse getByScheduleAndStudent(Long scheduleId, Long studentId) {
        return attendanceRepository.findByScheduleIdAndStudentId(scheduleId, studentId)
                .map(AttendanceResponse::fromEntity)
                .orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }
}
