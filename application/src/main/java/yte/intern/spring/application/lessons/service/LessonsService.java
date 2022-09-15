package yte.intern.spring.application.lessons.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.academician.service.AcademicianService;
import yte.intern.spring.application.admin.entitiy.Admin;
import yte.intern.spring.application.admin.repository.AdminRepository;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;
import yte.intern.spring.application.lessons.entity.Lessons;
import yte.intern.spring.application.lessons.repository.LessonsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonsService {
    private final LessonsRepository lessonsRepository;
    private final AcademicianService academicianService;


    public MessageResponse addLessons(Lessons lessons) {
        Academician academician = academicianService.getById(lessons.getAcademician().getId());
        lessons.setAcademician(academician);
        lessonsRepository.save(lessons);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been added successfully");
    }

    public List<Lessons> getAllLessons() {
        return lessonsRepository.findAll();
    }

    public Lessons getById(Long id) {
        return lessonsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
    }

    public MessageResponse deleteLessonsById(Long id) {
        lessonsRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been deleted successfully");
    }

    public MessageResponse updateLessons(Long id, Lessons updatedLessons) {
        Lessons lessons = lessonsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        Academician academician = academicianService.getById(updatedLessons.getAcademician().getId());
        updatedLessons.setAcademician(academician);
        lessons.update(updatedLessons);
        lessonsRepository.save(lessons);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been updated successfully");
    }
}
