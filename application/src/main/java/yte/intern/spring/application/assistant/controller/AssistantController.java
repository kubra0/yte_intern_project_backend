package yte.intern.spring.application.assistant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.assistant.controller.request.AddAssistantRequest;
import yte.intern.spring.application.assistant.controller.request.UpdateAssistantRequest;
import yte.intern.spring.application.assistant.controller.responses.AssistantQueryModel;
import yte.intern.spring.application.assistant.service.AssistantService;
import yte.intern.spring.application.authentication.entity.Authority;
import yte.intern.spring.application.authentication.entity.Users;
import yte.intern.spring.application.authentication.repository.AuthorityRepository;
import yte.intern.spring.application.authentication.repository.UserRepository;
import yte.intern.spring.application.common.response.MessageResponse;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assistants")
@RequiredArgsConstructor
@Validated
public class AssistantController {
    private final AssistantService assistantService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAssistant(@Valid @RequestBody AddAssistantRequest addAssistantRequest) {
        Users a = new Users();
        Authority assistant = authorityRepository.findByAuthority("ASSISTANT")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ASSISTANT not found!"));
        String hash = passwordEncoder.encode(addAssistantRequest.password());
        a.setPassword(hash);
        a.setUsername(addAssistantRequest.username());
        a.setAuthorities(List.of(assistant));
        userRepository.save(a);
        return assistantService.addAssistant(addAssistantRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AssistantQueryModel> getAllAssistants() {
        return assistantService.getAllAssistants()
                .stream()
                .map(assistant -> new AssistantQueryModel(assistant))
                .toList();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AssistantQueryModel getById(@NotNull @PathVariable Long id) {
        return new AssistantQueryModel(assistantService.getById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAssistantById(@PathVariable @NotNull Long id) {
        return assistantService.deleteAssistantById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateAssistant(@Valid @RequestBody UpdateAssistantRequest request, @PathVariable Long id) {
        return assistantService.updateAssistant(id, request.toDomainEntity());
    }
}
