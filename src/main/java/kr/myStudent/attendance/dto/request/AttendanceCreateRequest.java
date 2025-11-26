package kr.myStudent.attendance.dto.request;

import kr.myStudent.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceCreateRequest {
    private Long scheduleId;
    private Long studentId;
    private String userId;
    private AttendanceStatus status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String memo;
}
