package kr.myStudent.progress.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.progress.dto.request.ProgressCreateRequest;
import kr.myStudent.progress.dto.response.ProgressResponse;
import kr.myStudent.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<CommonResponse<ProgressResponse>> create(@RequestBody ProgressCreateRequest request) {
        ProgressResponse response = progressService.create(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/textbook/{textbookId}")
    public ResponseEntity<CommonResponse<List<ProgressResponse>>> getByTextbook(@PathVariable Long textbookId) {
        List<ProgressResponse> response = progressService.getByTextbook(textbookId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }
}
