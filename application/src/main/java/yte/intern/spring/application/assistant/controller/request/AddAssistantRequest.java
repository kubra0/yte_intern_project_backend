package yte.intern.spring.application.assistant.controller.request;

import yte.intern.spring.application.assistant.entity.Assistant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAssistantRequest(
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
    public Assistant toDomainEntity() {
        return new Assistant(name, surname, email, username, password);
    }
}
