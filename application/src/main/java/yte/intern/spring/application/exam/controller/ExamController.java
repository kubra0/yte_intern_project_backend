package yte.intern.spring.application.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.exam.controller.request.AddExamRequest;
import yte.intern.spring.application.exam.controller.request.UpdateExamRequest;
import yte.intern.spring.application.exam.controller.response.ExamQueryModel;
import yte.intern.spring.application.exam.service.ExamService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
@Validated
public class ExamController {
    private final ExamService examService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse addExam(@Valid @RequestBody AddExamRequest addExamRequest) {
        return examService.addExam(addExamRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT','ACADEMICIAN','ASSISTANT')")
    public List<ExamQueryModel> getAllExams() {
        return examService.getAllExams()
                .stream()
                .map(exam -> new ExamQueryModel(exam))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT','ACADEMICIAN','ASSISTANT')")
    public ExamQueryModel getById(@NotNull @PathVariable Long id) {
        return new ExamQueryModel(examService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse deleteExamById(@PathVariable @NotNull Long id) {
        return examService.deleteExamById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse updateExam(@Valid @RequestBody UpdateExamRequest request, @PathVariable Long id) {
        return examService.updateExam(id, request.toDomainEntity());
    }
}
