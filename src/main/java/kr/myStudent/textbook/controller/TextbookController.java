package kr.myStudent.textbook.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.textbook.dto.request.TextbookCreateRequest;
import kr.myStudent.textbook.dto.response.TextbookResponse;
import kr.myStudent.textbook.service.TextbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/textbooks")
public class TextbookController {

    private final TextbookService textbookService;

    @PostMapping
    public ResponseEntity<CommonResponse<TextbookResponse>> create(@RequestBody TextbookCreateRequest request) {
        TextbookResponse response = textbookService.create(request);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TextbookResponse>>> getByStudent(
            @RequestParam Long studentId,
            @RequestParam String userId) {
        List<TextbookResponse> response = textbookService.getByStudent(studentId, userId);
        return ResponseEntity.ok(CommonResponse.success(ResponseCode.SUCCESS, response));
    }
}
