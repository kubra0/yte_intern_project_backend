package yte.intern.spring.application.assistant.controller.request;

import yte.intern.spring.application.assistant.entity.Assistant;

public record UpdateAssistantRequest(
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public Assistant toDomainEntity() {
        return new Assistant(name, surname, email, username, password);
    }

}
