package yte.intern.spring.application.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yte.intern.spring.application.assistant.entity.Assistant;
import yte.intern.spring.application.assistant.service.AssistantService;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;
import yte.intern.spring.application.homework.entity.Homework;
import yte.intern.spring.application.homework.repository.HomeworkRepository;
import yte.intern.spring.application.lessons.entity.Lessons;
import yte.intern.spring.application.lessons.service.LessonsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {

    private  final HomeworkRepository homeworkRepository;

    private  final AssistantService assistantService;

    private final LessonsService lessonsService;


    public MessageResponse addHomework(Homework homework) {
        Lessons lessons = lessonsService.getById(homework.getLessons().getId());
        homework.setLessons(lessons);

        Assistant assistant=assistantService.getById(homework.getAssistant().getId());
        homework.setAssistant(assistant);

        homeworkRepository.save(homework);
        return new MessageResponse(ResponseType.SUCCESS, "Ödev başarıyla kaydedildi");
    }

    public MessageResponse updateHomework(Long id, Homework updateHomework) {
        Homework homework = homeworkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("kayıt bulunamadı"));

        Lessons lessons = lessonsService.getById(updateHomework.getLessons().getId());
        updateHomework.setLessons(lessons);
        homework.update(updateHomework);

        homeworkRepository.save(homework);
        return new MessageResponse(ResponseType.SUCCESS, "Ödev başarıyla güncellendi");
    }

    public MessageResponse deleteHomeworkById(Long id) {
        homeworkRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Ödev başarıyla silindi");
    }

    public List<Homework> getAllHomework() {
        return homeworkRepository.findAll();
    }


    public Homework getHomeworkById(Long id) {
        return homeworkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("kayıt bulunamadı"));
    }

    @GetMapping("{id}")
    public Homework getById(@PathVariable Long id) {
        return homeworkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("kayıt bulunamadı"));

    }
}
