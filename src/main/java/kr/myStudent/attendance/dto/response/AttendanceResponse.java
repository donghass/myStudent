package kr.myStudent.attendance.dto.response;

import kr.myStudent.attendance.domain.AttendanceEntity;
import kr.myStudent.enums.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendanceResponse {
    private Long id;
    private Long scheduleId;
    private Long studentId;
    private String userId;
    private AttendanceStatus status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AttendanceResponse fromEntity(AttendanceEntity entity) {
        return AttendanceResponse.builder()
                .id(entity.getId())
                .scheduleId(entity.getScheduleId())
                .studentId(entity.getStudentId())
                .userId(entity.getUserId())
                .status(entity.getStatus())
                .checkInTime(entity.getCheckInTime())
                .checkOutTime(entity.getCheckOutTime())
                .memo(entity.getMemo())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
