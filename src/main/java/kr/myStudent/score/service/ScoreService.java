package kr.myStudent.score.service;

import kr.myStudent.score.domain.ScoreEntity;
import kr.myStudent.score.domain.ScoreRepository;
import kr.myStudent.score.dto.request.ScoreCreateRequest;
import kr.myStudent.score.dto.request.ScoreUpdateRequest;
import kr.myStudent.score.dto.response.ScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    /** 등록 */
    public ScoreResponse create(ScoreCreateRequest req) {

        ScoreEntity entity = ScoreEntity.builder()
                .userId(req.getUserId())
                .studentId(req.getStudentId())
                .subject(req.getSubject())
                .score(req.getScore())
                .testName(req.getTestName())
                .testDate(req.getTestDate())
                .build();

        scoreRepository.save(entity);

        return ScoreResponse.fromEntity(entity);
    }

    /** 단건 조회 */
    public ScoreResponse getOne(Long scoreId) {
        ScoreEntity entity = scoreRepository.findById(scoreId)
                .orElseThrow(() -> new IllegalArgumentException("점수를 찾을 수 없습니다."));
        return ScoreResponse.fromEntity(entity);
    }

    /** userId 기준 전체 조회 */
    public List<ScoreResponse> getByUserId(String userId) {
        return scoreRepository.findByUserId(userId).stream()
                .map(ScoreResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** userId + studentId 기준 전체 조회 */
    public List<ScoreResponse> getByUserIdAndStudentId(String userId, Long studentId) {
        return scoreRepository.findByUserIdAndStudentId(userId, studentId).stream()
                .map(ScoreResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /** 수정 */
    public ScoreResponse update(Long scoreId, ScoreUpdateRequest req) {
        ScoreEntity entity = scoreRepository.findById(scoreId)
                .orElseThrow(() -> new IllegalArgumentException("점수를 찾을 수 없습니다."));
        entity.updateScore(req);
        scoreRepository.save(entity);
        return ScoreResponse.fromEntity(entity);
    }

    /** 삭제 */
    public void delete(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }
}
