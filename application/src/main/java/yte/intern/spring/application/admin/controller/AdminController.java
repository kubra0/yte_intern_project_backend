package yte.intern.spring.application.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.application.admin.controller.request.AddAdminRequest;
import yte.intern.spring.application.admin.controller.request.UpdateAdminRequest;
import yte.intern.spring.application.admin.controller.responses.AdminQueryModel;
import yte.intern.spring.application.admin.service.AdminService;
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
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {
    private final AdminService adminService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;



    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAdmin(@Valid @RequestBody AddAdminRequest addAdminRequest) {
        Users a = new Users();
        Authority admin = authorityRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ADMIN not found!"));
        String hash = passwordEncoder.encode(addAdminRequest.password());
        a.setPassword(hash);
        a.setUsername(addAdminRequest.username());
        a.setAuthorities(List.of(admin));
        userRepository.save(a);
        return adminService.addAdmin(addAdminRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AdminQueryModel> getAllAdmins() {
        return adminService.getAllAdmins()
                .stream()
                .map(admin -> new AdminQueryModel(admin))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AdminQueryModel getById(@NotNull @PathVariable Long id) {
        return new AdminQueryModel(adminService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAdminById(@PathVariable @NotNull Long id) {
        return adminService.deleteAdminById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateAdmin(@Valid @RequestBody UpdateAdminRequest request, @PathVariable Long id) {
        return adminService.updateAdmin(id, request.toDomainEntity());
    }
}
