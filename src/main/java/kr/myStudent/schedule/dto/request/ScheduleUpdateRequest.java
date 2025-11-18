package kr.myStudent.schedule.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleUpdateRequest {
    private Subject subject;
    private LocalDateTime classDate;
    private String classLocation;
    private String content;
}
