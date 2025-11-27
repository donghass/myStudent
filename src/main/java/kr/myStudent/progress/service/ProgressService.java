package kr.myStudent.progress.service;

import kr.myStudent.progress.domain.ProgressEntity;
import kr.myStudent.progress.domain.ProgressRepository;
import kr.myStudent.progress.dto.request.ProgressCreateRequest;
import kr.myStudent.progress.dto.response.ProgressResponse;
import kr.myStudent.textbook.domain.TextbookEntity;
import kr.myStudent.textbook.repository.TextbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final TextbookRepository textbookRepository;

    @Transactional
    public ProgressResponse create(ProgressCreateRequest request) {
        // 1. Calculate lesson count
        Integer currentCount = progressRepository.countByTextbookId(request.getTextbookId());
        Integer nextLessonCount = currentCount + 1;

        // 2. Create Progress
        ProgressEntity progress = ProgressEntity.builder()
                .textbookId(request.getTextbookId())
                .studentId(request.getStudentId())
                .userId(request.getUserId())
                .lessonCount(nextLessonCount)
                .lessonDate(LocalDateTime.parse(request.getLessonDate(), DateTimeFormatter.ISO_DATE_TIME))
                .unit(request.getUnit())
                .pageStart(request.getPageStart())
                .pageEnd(request.getPageEnd())
                .understanding(request.getUnderstanding())
                .memo(request.getMemo())
                .build();

        progressRepository.save(progress);

        // 3. Update Textbook current unit
        TextbookEntity textbook = textbookRepository.findById(request.getTextbookId())
                .orElseThrow(() -> new IllegalArgumentException("Textbook not found"));

        textbook.updateCurrentUnit(textbook.getCurrentUnit() + 1);

        return ProgressResponse.fromEntity(progress);
    }

    public List<ProgressResponse> getByTextbook(Long textbookId) {
        return progressRepository.findByTextbookId(textbookId).stream()
                .map(ProgressResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
