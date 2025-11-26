package kr.myStudent.progress.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Getter;

@Getter
public class ProgressCreateRequest {
    private Long studentId;
    private String userId;
    private Subject subject;
    private String lessonDate;   // "2025-11-26T18:00"
    private String content;
    private String homework;
    private String memo;
}
