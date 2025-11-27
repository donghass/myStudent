package kr.myStudent.progress.dto.request;

import kr.myStudent.enums.Understanding;
import lombok.Getter;

@Getter
public class ProgressCreateRequest {
    private Long textbookId;
    private Long studentId;
    private String userId;
    private String lessonDate;
    private String unit;
    private Integer pageStart;
    private Integer pageEnd;
    private Understanding understanding;
    private String memo;
}
