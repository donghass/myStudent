package kr.myStudent.progress.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Getter;

@Getter
public class ProgressUpdateRequest {
    private Subject subject;
    private String lessonDate;
    private String content;
    private String homework;
    private String memo;
}
