package yte.intern.spring.application.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.homework.controller.request.AddHomeworkRequest;
import yte.intern.spring.application.homework.controller.request.UpdateHomeworkRequest;
import yte.intern.spring.application.homework.controller.response.HomeworkQueryModel;
import yte.intern.spring.application.homework.service.HomeworkService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/homeworks")
@Validated
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse addHomework(@Valid @RequestBody AddHomeworkRequest addHomeworkRequest) {
        return homeworkService.addHomework(addHomeworkRequest.toDomainEntity());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse updateHomework(@Valid @RequestBody UpdateHomeworkRequest updateHomeworkRequest, @PathVariable Long id) {
        return homeworkService.updateHomework(id, updateHomeworkRequest.toDomainEntity());

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT')")
    public MessageResponse getHomeworkById(@PathVariable Long id) {
        return homeworkService.deleteHomeworkById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT','STUDENT')")
    public List<HomeworkQueryModel> getAllHomeworks() {
        return homeworkService.getAllHomework()
                .stream()
                .map(homework -> new HomeworkQueryModel(homework))
                .toList();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ACADEMICIAN','ASSISTANT','STUDENT')")
    public HomeworkQueryModel getById(@PathVariable Long id) {
        return new HomeworkQueryModel(homeworkService.getById(id));
    }

}
