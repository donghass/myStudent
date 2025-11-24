package kr.myStudent.student.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubjectCreateRequest {
    private Long studentId;
    private String userId;
    private String subject;
    private Integer price;
}
