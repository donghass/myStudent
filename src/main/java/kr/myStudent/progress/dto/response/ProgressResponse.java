package kr.myStudent.progress.dto.response;

import kr.myStudent.enums.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProgressResponse {

    private Long progressId;
    private Long studentId;
    private String studentName;
    private Subject subject;
    private String lessonDate;
    private String content;
    private String homework;
    private String memo;
}
