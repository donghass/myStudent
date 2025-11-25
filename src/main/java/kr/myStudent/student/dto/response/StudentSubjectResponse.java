package kr.myStudent.student.dto.response;

import kr.myStudent.enums.Subject;
import kr.myStudent.student.domain.StudentSubjectEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentSubjectResponse {

    private Long studentSubjectId;
    private Long studentId;
    private String userId;
    private Subject subject;
    private Integer price;

    public static StudentSubjectResponse fromEntity(StudentSubjectEntity e) {
        return StudentSubjectResponse.builder()
                .studentSubjectId(e.getStudentSubjectId())
                .studentId(e.getStudentId())
                .userId(e.getUserId())
                .subject(e.getSubject().name())
                .price(e.getPrice())
                .build();
    }
}
