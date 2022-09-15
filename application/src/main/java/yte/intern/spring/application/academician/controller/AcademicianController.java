package yte.intern.spring.application.academician.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.authentication.entity.Authority;
import yte.intern.spring.application.authentication.entity.Users;
import yte.intern.spring.application.authentication.repository.AuthorityRepository;
import yte.intern.spring.application.authentication.repository.UserRepository;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.academician.controller.request.AddAcademicianRequest;
import yte.intern.spring.application.academician.controller.request.UpdateAcademicianRequest;
import yte.intern.spring.application.academician.controller.responses.AcademicianQueryModel;
import yte.intern.spring.application.academician.service.AcademicianService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/academicians")
@RequiredArgsConstructor
@Validated
public class AcademicianController {
    private final AcademicianService academicianService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAcademician(@Valid @RequestBody AddAcademicianRequest addAcademicianRequest) {
        Users a = new Users();
        Authority academician = authorityRepository.findByAuthority("ACADEMICIAN")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ACADEMICIAN not found!"));
        String hash = passwordEncoder.encode(addAcademicianRequest.password());
        a.setPassword(hash);
        a.setUsername(addAcademicianRequest.username());
        a.setAuthorities(List.of(academician));
        userRepository.save(a);
        return academicianService.addAcademician(addAcademicianRequest.toDomainEntity());
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AcademicianQueryModel> getAllAcademicians() {
        return academicianService.getAllAcademicians()
                .stream()
                .map(academician -> new AcademicianQueryModel(academician))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AcademicianQueryModel getById(@NotNull @PathVariable Long id) {
        return new AcademicianQueryModel(academicianService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAcademicianById(@PathVariable @NotNull Long id) {
        return academicianService.deleteAcademicianById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateAcademician(@Valid @RequestBody UpdateAcademicianRequest request, @PathVariable Long id) {
        return academicianService.updateAcademician(id, request.toDomainEntity());
    }
}
