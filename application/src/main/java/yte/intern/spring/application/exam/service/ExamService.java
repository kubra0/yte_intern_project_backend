package yte.intern.spring.application.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;
import yte.intern.spring.application.exam.entity.Exam;
import yte.intern.spring.application.exam.repository.ExamRepository;
import yte.intern.spring.application.lessons.entity.Lessons;
import yte.intern.spring.application.lessons.service.LessonsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final LessonsService lessonsService;

    public MessageResponse addExam(Exam exam) {
        Lessons lessons = lessonsService.getById(exam.getLessons().getId());
        exam.setLessons(lessons);

        examRepository.save(exam);

        return new MessageResponse(ResponseType.SUCCESS, "Exam has been added successfully");
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
    }

    public MessageResponse deleteExamById(Long id) {
        examRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Exam has been deleted successfully");
    }

    public MessageResponse updateExam(Long id, Exam updatedExam) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));

        Lessons lessons = lessonsService.getById(updatedExam.getLessons().getId());
        updatedExam.setLessons(lessons);
        exam.update(updatedExam);

        examRepository.save(exam);

        return new MessageResponse(ResponseType.SUCCESS, "Exam has been updated successfully");
    }
}
