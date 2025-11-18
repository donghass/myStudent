package kr.myStudent.student.dto.request;

import lombok.Data;

@Data
public class StudentUpdateRequest {
    private String name;
    private String tel;
    private Integer age;
    private String memo;
    private String parentTel;
}
