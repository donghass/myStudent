package kr.myStudent.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ms_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @Column(name = "student_id", length = 20)
    private String studentId;   // 학생아이디 (PK)

    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;      // 선생아이디 (FK)

    @Column(name = "name", length = 20, nullable = false)
    private String name;        // 학생이름

    @Column(name = "tel", length = 20)
    private String tel;         // 학생번호

    @Column(name = "age")
    private Integer age;        // 나이

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;        // 메모

    @Column(name = "parent_tel", length = 20)
    private String parentTel;   // 학부모번호
}
