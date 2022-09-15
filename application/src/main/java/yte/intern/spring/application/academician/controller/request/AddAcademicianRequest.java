package yte.intern.spring.application.academician.controller.request;

import yte.intern.spring.application.academician.entity.Academician;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAcademicianRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        @Email
        String email,
        @NotBlank
        String username,
        @NotBlank
        String password

) {
    public Academician toDomainEntity() {
        return new Academician(name, surname, email, username, password);
    }
}
