package kr.myStudent.homework.service;

import kr.myStudent.homework.domain.HomeworkEntity;
import kr.myStudent.homework.domain.HomeworkRepository;
import kr.myStudent.homework.dto.request.HomeworkCreateRequest;
import kr.myStudent.homework.dto.request.HomeworkUpdateRequest;
import kr.myStudent.homework.dto.response.HomeworkResponse;
import kr.myStudent.progress.domain.ProgressEntity;
import kr.myStudent.progress.domain.ProgressRepository;
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
    private final ProgressRepository progressRepository;

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

    public List<HomeworkResponse> getRecentHomeworkByStudent(Long studentId, String userId) {
        List<ProgressEntity> progressList = progressRepository.findByStudent(userId, studentId);
        if (progressList.isEmpty()) {
            return List.of();
        }

        ProgressEntity targetProgress = progressList.get(0);

        // 오늘 수업(Progress)이 이미 기록되었다면, 그 전 수업의 숙제를 가져와야 함
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate latestDate = targetProgress.getLessonDate().toLocalDate();

        if (!latestDate.isBefore(today)) {
            if (progressList.size() > 1) {
                targetProgress = progressList.get(1);
            } else {
                // 오늘이 첫 수업인 경우 지난 숙제 없음
                return List.of();
            }
        }

        return homeworkRepository.findByProgressId(targetProgress.getProgressId()).stream()
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
