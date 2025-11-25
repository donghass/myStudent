package kr.myStudent.student.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubjectCreateRequest {
    private Long studentId;
    private String userId;
    private Subject subject;
    private Integer price;
}
