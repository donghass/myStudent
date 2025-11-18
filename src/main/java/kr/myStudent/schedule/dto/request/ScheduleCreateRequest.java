package kr.myStudent.schedule.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleCreateRequest {
    private Subject subject;
    private String studentId;
    private String userId;
    private LocalDateTime classDate;
    private String classLocation;
    private String content;
}
