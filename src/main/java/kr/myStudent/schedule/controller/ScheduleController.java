package kr.myStudent.schedule.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.schedule.dto.request.ScheduleCreateRequest;
import kr.myStudent.schedule.dto.request.ScheduleUpdateRequest;
import kr.myStudent.schedule.dto.response.ScheduleResponse;
import kr.myStudent.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /** 등록 */
    @PostMapping
    public ResponseEntity<CommonResponse<ScheduleResponse>> create(
            @RequestBody ScheduleCreateRequest req
    ) {
        ScheduleResponse result = scheduleService.create(req);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponse>> getOne(@PathVariable Long id) {
        ScheduleResponse result = scheduleService.getOne(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** userId 기준 전체 조회 */
    @GetMapping
    public ResponseEntity<CommonResponse<List<ScheduleResponse>>> getByUserId(
            @RequestParam String userId
    ) {
        List<ScheduleResponse> result = scheduleService.getByUserId(userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** userId + studentId 조회 */
    @GetMapping("/student")
    public ResponseEntity<CommonResponse<List<ScheduleResponse>>> getByUserIdAndStudentId(
            @RequestParam String userId,
            @RequestParam String studentId
    ) {
        List<ScheduleResponse> result = scheduleService.getByUserIdAndStudentId(userId, studentId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponse>> update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest req
    ) {
        ScheduleResponse result = scheduleService.update(id, req);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, "삭제 완료"));
    }

    /** 날짜 범위 조회 */
    @GetMapping("/range")
    public ResponseEntity<CommonResponse<List<ScheduleResponse>>> getDateRange(
            @RequestParam String userId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        List<ScheduleResponse> result = scheduleService.getDateRange(userId, startDate, endDate);

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 오늘의 수업 조회 */
    @GetMapping("/today")
    public ResponseEntity<CommonResponse<List<ScheduleResponse>>> getToday(
            @RequestParam String userId
    ) {
        List<ScheduleResponse> result = scheduleService.getTodaySchedules(userId);

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }

    /** 월간 캘린더 API */
    @GetMapping("/calendar")
    public ResponseEntity<CommonResponse<List<ScheduleResponse>>> getMonthly(
            @RequestParam String userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        List<ScheduleResponse> result = scheduleService.getMonthlySchedules(userId, year, month);

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, result));
    }
}
