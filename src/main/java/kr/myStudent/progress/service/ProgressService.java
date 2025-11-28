package kr.myStudent.progress.service;

import kr.myStudent.homework.domain.HomeworkEntity;
import kr.myStudent.homework.domain.HomeworkRepository;
import kr.myStudent.homework.dto.response.HomeworkResponse;
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
    private final HomeworkRepository homeworkRepository;

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

        // 3. Create Homeworks if provided
        List<HomeworkResponse> homeworkResponses = null;
        if (request.getHomeworks() != null && !request.getHomeworks().isEmpty()) {
            homeworkResponses = request.getHomeworks().stream()
                    .map(homeworkItem -> {
                        HomeworkEntity homework = HomeworkEntity.builder()
                                .progressId(progress.getProgressId())
                                .textbookId(request.getTextbookId())
                                .studentId(request.getStudentId())
                                .userId(request.getUserId())
                                .content(homeworkItem.getContent())
                                .dueDate(homeworkItem.getDueDate() != null && !homeworkItem.getDueDate().isEmpty()
                                        ? LocalDateTime.parse(homeworkItem.getDueDate(), DateTimeFormatter.ISO_DATE_TIME)
                                        : null)
                                .isCompleted(false)
                                .build();
                        homeworkRepository.save(homework);
                        return HomeworkResponse.fromEntity(homework);
                    })
                    .collect(Collectors.toList());
        }

        // 4. Update Textbook current unit
        TextbookEntity textbook = textbookRepository.findById(request.getTextbookId())
                .orElseThrow(() -> new IllegalArgumentException("Textbook not found"));

        textbook.updateCurrentUnit(textbook.getCurrentUnit() + 1);

        return ProgressResponse.fromEntity(progress, homeworkResponses);
    }

    public List<ProgressResponse> getByTextbook(Long textbookId) {
        return progressRepository.findByTextbookId(textbookId).stream()
                .map(progress -> {
                    List<HomeworkResponse> homeworks = homeworkRepository.findByProgressId(progress.getProgressId())
                            .stream()
                            .map(HomeworkResponse::fromEntity)
                            .collect(Collectors.toList());
                    return ProgressResponse.fromEntity(progress, homeworks);
                })
                .collect(Collectors.toList());
    }
}
