package kr.myStudent.student.dto.request;

import kr.myStudent.enums.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubjectUpdateRequest {
    private Subject subject;
    private Integer price;
}
