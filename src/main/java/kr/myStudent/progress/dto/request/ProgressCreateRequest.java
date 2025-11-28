package kr.myStudent.progress.dto.request;

import kr.myStudent.enums.Understanding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressCreateRequest {
    private Long textbookId;
    private Long studentId;
    private Long scheduleId; // 선택된 일정 ID
    private String userId;
    private String lessonDate;
    private String unit;
    private Integer pageStart;
    private Integer pageEnd;
    private Understanding understanding;
    private String memo;
    private List<HomeworkItem> homeworks; // 숙제 리스트

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeworkItem {
        private String content;
        private String dueDate; // ISO format string, optional
    }
}
