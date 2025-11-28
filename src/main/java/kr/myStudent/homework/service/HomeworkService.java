package kr.myStudent.homework.service;

import kr.myStudent.homework.domain.HomeworkEntity;
import kr.myStudent.homework.domain.HomeworkRepository;
import kr.myStudent.homework.dto.request.HomeworkCreateRequest;
import kr.myStudent.homework.dto.request.HomeworkUpdateRequest;
import kr.myStudent.homework.dto.response.HomeworkResponse;
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
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;

    @Transactional
    public HomeworkResponse create(HomeworkCreateRequest request) {
        HomeworkEntity homework = HomeworkEntity.builder()
                .progressId(request.getProgressId())
                .textbookId(request.getTextbookId())
                .studentId(request.getStudentId())
                .userId(request.getUserId())
                .content(request.getContent())
                .dueDate(request.getDueDate() != null && !request.getDueDate().isEmpty()
                        ? LocalDateTime.parse(request.getDueDate(), DateTimeFormatter.ISO_DATE_TIME)
                        : null)
                .isCompleted(false)
                .build();

        homeworkRepository.save(homework);
        return HomeworkResponse.fromEntity(homework);
    }

    @Transactional
    public HomeworkResponse update(Long homeworkId, String userId, HomeworkUpdateRequest request) {
        HomeworkEntity homework = homeworkRepository.findByHomeworkIdAndUserId(homeworkId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Homework not found"));

        if (request.getContent() != null) {
            homework.updateContent(request.getContent());
        }

        if (request.getDueDate() != null) {
            LocalDateTime dueDate = request.getDueDate().isEmpty()
                    ? null
                    : LocalDateTime.parse(request.getDueDate(), DateTimeFormatter.ISO_DATE_TIME);
            homework.updateDueDate(dueDate);
        }

        if (request.getIsCompleted() != null) {
            if (request.getIsCompleted()) {
                homework.complete();
            } else {
                homework.uncomplete();
            }
        }

        return HomeworkResponse.fromEntity(homework);
    }

    public List<HomeworkResponse> getByProgressId(Long progressId) {
        return homeworkRepository.findByProgressId(progressId).stream()
                .map(HomeworkResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<HomeworkResponse> getByStudentId(Long studentId, String userId) {
        return homeworkRepository.findByStudentIdAndUserId(studentId, userId).stream()
                .map(HomeworkResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<HomeworkResponse> getByTextbookId(Long textbookId) {
        return homeworkRepository.findByTextbookId(textbookId).stream()
                .map(HomeworkResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long homeworkId, String userId) {
        HomeworkEntity homework = homeworkRepository.findByHomeworkIdAndUserId(homeworkId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Homework not found"));
        homeworkRepository.delete(homework);
    }
}

