package kr.myStudent.score.dto.request;

import kr.myStudent.enums.Subject;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScoreCreateRequest {
    private String userId;
    private Long studentId;
    private Subject subject;
    private Double score;
    private String testName;
    private LocalDate testDate;
}
