package kr.myStudent.attendance.dto.request;

import kr.myStudent.enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceUpdateRequest {
    private AttendanceStatus status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String memo;
}
