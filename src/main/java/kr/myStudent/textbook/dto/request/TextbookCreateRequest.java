package kr.myStudent.textbook.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextbookCreateRequest {
    private Long studentId;
    private String userId;
    private String title;
    private Subject subject;
    private Integer totalUnit;
}
