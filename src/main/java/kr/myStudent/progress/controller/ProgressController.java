package kr.myStudent.progress.controller;

import kr.myStudent.common.response.*;
import kr.myStudent.progress.dto.request.ProgressCreateRequest;
import kr.myStudent.progress.dto.request.ProgressUpdateRequest;
import kr.myStudent.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService service;

    /** 등록 */
    @PostMapping
    public ResponseEntity<CommonResponse<?>> create(@RequestBody ProgressCreateRequest req) {
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, service.create(req)));
    }

    /** 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> update(
            @PathVariable Long id,
            @RequestBody ProgressUpdateRequest req) {

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, service.update(id, req)));
    }

    /** 전체 학생 최신 진도 1줄 조회 */
    @GetMapping("/latest")
    public ResponseEntity<CommonResponse<?>> getLatest(
            @RequestParam String userId) {

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, service.getLatest(userId)));
    }

    /** 특정 학생 전체 진도 조회 */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<CommonResponse<?>> getByStudent(
            @RequestParam String userId,
            @PathVariable Long studentId) {

        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, service.getByStudent(userId, studentId)));
    }
}
