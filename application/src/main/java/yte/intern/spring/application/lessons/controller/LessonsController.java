package yte.intern.spring.application.lessons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.lessons.controller.request.AddLessonsRequest;
import yte.intern.spring.application.lessons.controller.request.UpdateLessonsRequest;
import yte.intern.spring.application.lessons.controller.responses.LessonsQueryModel;
import yte.intern.spring.application.lessons.service.LessonsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Validated
public class LessonsController {
    private final LessonsService lessonsService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addLessons(@Valid @RequestBody AddLessonsRequest addLessonsRequest) {
        return lessonsService.addLessons(addLessonsRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','ACADEMICIAN', 'ASSISTANT')")
    public List<LessonsQueryModel> getAllLessons() {
        return lessonsService.getAllLessons()
                .stream()
                .map(lessons -> new LessonsQueryModel(lessons))
                .toList();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','ACADEMICIAN', 'ASSISTANT')")
    public LessonsQueryModel getById(@NotNull @PathVariable Long id) {
        return new LessonsQueryModel(lessonsService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteLessonsById(@PathVariable @NotNull Long id) {
        return lessonsService.deleteLessonsById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateLessons(@Valid @RequestBody UpdateLessonsRequest request, @PathVariable Long id) {
        return lessonsService.updateLessons(id, request.toDomainEntity());
    }
}
