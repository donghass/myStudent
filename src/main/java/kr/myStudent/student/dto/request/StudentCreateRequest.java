package kr.myStudent.student.dto.request;

import lombok.Data;

@Data
public class StudentCreateRequest {
    private String studentId;
    private String userId;
    private String name;
    private String tel;
    private Integer age;
    private String memo;
    private String parentTel;
}
