package kr.myStudent.schedule.dto.response;

import kr.myStudent.schedule.domain.ScheduleEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleResponse {

    private Long scheduleId;
    private String subject;
    private Long studentId;
    private String userId;
    private String classDate;
    private String classLocation;
    private String content;

    public static ScheduleResponse fromEntity(ScheduleEntity e) {
        return ScheduleResponse.builder()
                .scheduleId(e.getScheduleId())
                .subject(e.getSubject().name())
                .studentId(e.getStudentId())
                .userId(e.getUserId())
                .classDate(e.getClassDate() != null ? e.getClassDate().toString() : null)
                .classLocation(e.getClassLocation())
                .content(e.getContent())
                .build();
    }
}
