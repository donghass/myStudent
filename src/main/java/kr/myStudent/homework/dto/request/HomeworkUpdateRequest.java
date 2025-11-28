package kr.myStudent.homework.dto.request;

import lombok.Getter;

@Getter
public class HomeworkUpdateRequest {
    private String content;
    private String dueDate; // ISO format string
    private Boolean isCompleted;
}

