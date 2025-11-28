package kr.myStudent.homework.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.homework.dto.request.HomeworkCreateRequest;
import kr.myStudent.homework.dto.request.HomeworkUpdateRequest;
import kr.myStudent.homework.dto.response.HomeworkResponse;
import kr.myStudent.homework.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;

    @PostMapping
    public ResponseEntity<CommonResponse<HomeworkResponse>> create(@RequestBody HomeworkCreateRequest request) {
        HomeworkResponse response = homeworkService.create(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @PutMapping("/{homeworkId}")
    public ResponseEntity<CommonResponse<HomeworkResponse>> update(
            @PathVariable Long homeworkId,
            @RequestHeader("UserId") String userId,
            @RequestBody HomeworkUpdateRequest request) {
        HomeworkResponse response = homeworkService.update(homeworkId, userId, request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/progress/{progressId}")
    public ResponseEntity<CommonResponse<List<HomeworkResponse>>> getByProgressId(@PathVariable Long progressId) {
        List<HomeworkResponse> response = homeworkService.getByProgressId(progressId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<CommonResponse<List<HomeworkResponse>>> getByStudentId(
            @PathVariable Long studentId,
            @RequestHeader("UserId") String userId) {
        List<HomeworkResponse> response = homeworkService.getByStudentId(studentId, userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/textbook/{textbookId}")
    public ResponseEntity<CommonResponse<List<HomeworkResponse>>> getByTextbookId(@PathVariable Long textbookId) {
        List<HomeworkResponse> response = homeworkService.getByTextbookId(textbookId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @DeleteMapping("/{homeworkId}")
    public ResponseEntity<CommonResponse<String>> delete(
            @PathVariable Long homeworkId,
            @RequestHeader("UserId") String userId) {
        homeworkService.delete(homeworkId, userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, "숙제가 삭제되었습니다."));
    }
}

