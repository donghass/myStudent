package kr.myStudent.student.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubjectUpdateRequest {
    private String subject;
    private Integer price;
}
