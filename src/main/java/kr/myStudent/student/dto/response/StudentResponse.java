package kr.myStudent.student.dto.response;


import kr.myStudent.student.domain.StudentEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {

    private String studentId;
    private String userId;
    private String name;
    private String tel;
    private Integer age;
    private String memo;
    private String parentTel;

    public static StudentResponse fromEntity(StudentEntity e) {
        return StudentResponse.builder()
                .studentId(e.getId().getStudentId())
                .userId(e.getId().getUserId())
                .name(e.getName())
                .tel(e.getTel())
                .age(e.getAge())
                .memo(e.getMemo())
                .parentTel(e.getParentTel())
                .build();
    }
}
