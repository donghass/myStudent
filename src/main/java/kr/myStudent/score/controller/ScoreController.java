package kr.myStudent.score.controller;

import kr.myStudent.common.response.CommonResponse;
import kr.myStudent.common.response.ResponseCode;
import kr.myStudent.score.dto.request.ScoreCreateRequest;
import kr.myStudent.score.dto.request.ScoreUpdateRequest;
import kr.myStudent.score.dto.response.ScoreResponse;
import kr.myStudent.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    /** 등록 */
    @PostMapping
    public ResponseEntity<CommonResponse<ScoreResponse>> create(@RequestBody ScoreCreateRequest req) {

        ScoreResponse result = scoreService.create(req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 단건 조회 */
    @GetMapping("/{scoreId}")
    public ResponseEntity<CommonResponse<ScoreResponse>> getOne(@PathVariable Long scoreId) {

        ScoreResponse result = scoreService.getOne(scoreId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** userId 기준 전체 조회 */
    @GetMapping
    public ResponseEntity<CommonResponse<List<ScoreResponse>>> getByUserId(
            @RequestParam String userId
    ) {
        List<ScoreResponse> result = scoreService.getByUserId(userId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** userId + studentId 기준 전체 조회 */
    @GetMapping("/student")
    public ResponseEntity<CommonResponse<List<ScoreResponse>>> getByUserIdAndStudentId(
            @RequestParam String userId,
            @RequestParam Long studentId
    ) {
        List<ScoreResponse> result = scoreService.getByUserIdAndStudentId(userId, studentId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 수정 */
    @PutMapping("/{scoreId}")
    public ResponseEntity<CommonResponse<ScoreResponse>> update(
            @PathVariable Long scoreId,
            @RequestBody ScoreUpdateRequest req
    ) {
        ScoreResponse result = scoreService.update(scoreId, req);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, result)
        );
    }

    /** 삭제 */
    @DeleteMapping("/{scoreId}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable Long scoreId) {

        scoreService.delete(scoreId);

        return ResponseEntity.ok(
                CommonResponse.success(ResponseCode.SUCCESS, "삭제 완료")
        );
    }
}
