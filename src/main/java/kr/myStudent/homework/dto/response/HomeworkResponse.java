package kr.myStudent.homework.dto.response;

import kr.myStudent.homework.domain.HomeworkEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class HomeworkResponse {

    private Long homeworkId;
    private Long progressId;
    private Long textbookId;
    private Long studentId;
    private String content;
    private String dueDate;
    private Boolean isCompleted;
    private String completedAt;
    private String createdAt;

    public static HomeworkResponse fromEntity(HomeworkEntity entity) {
        return HomeworkResponse.builder()
                .homeworkId(entity.getHomeworkId())
                .progressId(entity.getProgressId())
                .textbookId(entity.getTextbookId())
                .studentId(entity.getStudentId())
                .content(entity.getContent())
                .dueDate(entity.getDueDate() != null
                        ? entity.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        : null)
                .isCompleted(entity.getIsCompleted())
                .completedAt(entity.getCompletedAt() != null
                        ? entity.getCompletedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        : null)
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}

