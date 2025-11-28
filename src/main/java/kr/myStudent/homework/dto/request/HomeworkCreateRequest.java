package kr.myStudent.homework.dto.request;

import lombok.Getter;

@Getter
public class HomeworkCreateRequest {
    private Long progressId;
    private Long textbookId;
    private Long studentId;
    private String userId;
    private String content;
    private String dueDate; // ISO format string
}

