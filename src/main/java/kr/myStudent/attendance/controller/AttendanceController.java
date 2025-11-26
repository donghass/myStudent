package kr.myStudent.attendance.controller;

import kr.myStudent.attendance.dto.request.AttendanceCreateRequest;
import kr.myStudent.attendance.dto.request.AttendanceUpdateRequest;
import kr.myStudent.attendance.dto.response.AttendanceResponse;
import kr.myStudent.attendance.service.AttendanceService;
import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<CommonResponse<AttendanceResponse>> create(@RequestBody AttendanceCreateRequest request) {
        AttendanceResponse response = attendanceService.create(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<AttendanceResponse>> update(
            @PathVariable Long id,
            @RequestBody AttendanceUpdateRequest request) {
        AttendanceResponse response = attendanceService.update(id, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<AttendanceResponse>> getById(@PathVariable Long id) {
        AttendanceResponse response = attendanceService.getById(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/student/{studentId}/{userId}")
    public ResponseEntity<CommonResponse<List<AttendanceResponse>>> getByStudent(
            @PathVariable Long studentId,
            @PathVariable String userId) {
        List<AttendanceResponse> responses = attendanceService.getByStudentId(studentId, userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, responses));
    }

    @GetMapping("/schedule/{scheduleId}/student/{studentId}")
    public ResponseEntity<CommonResponse<AttendanceResponse>> getByScheduleAndStudent(
            @PathVariable Long scheduleId,
            @PathVariable Long studentId) {
        AttendanceResponse response = attendanceService.getByScheduleAndStudent(scheduleId, studentId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> delete(@PathVariable Long id) {
        attendanceService.delete(id);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS));
    }
}
