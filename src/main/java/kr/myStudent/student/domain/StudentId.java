package kr.myStudent.student.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentId implements Serializable {

    private String studentId;  // 학생 아이디 (PK1)
    private String userId;     // 선생 아이디 (PK2)
}
