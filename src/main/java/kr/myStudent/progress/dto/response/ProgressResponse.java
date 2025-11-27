package kr.myStudent.progress.dto.response;

import kr.myStudent.progress.domain.ProgressEntity;
import kr.myStudent.enums.Understanding;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ProgressResponse {

    private Long progressId;
    private Long textbookId;
    private Long studentId;
    private Integer lessonCount;
    private String lessonDate;
    private String unit;
    private Integer pageStart;
    private Integer pageEnd;
    private Understanding understanding;
    private String memo;

    public static ProgressResponse fromEntity(ProgressEntity entity) {
        return ProgressResponse.builder()
                .progressId(entity.getProgressId())
                .textbookId(entity.getTextbookId())
                .studentId(entity.getStudentId())
                .lessonCount(entity.getLessonCount())
                .lessonDate(entity.getLessonDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .unit(entity.getUnit())
                .pageStart(entity.getPageStart())
                .pageEnd(entity.getPageEnd())
                .understanding(entity.getUnderstanding())
                .memo(entity.getMemo())
                .build();
    }
}
