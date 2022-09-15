package yte.intern.spring.application.assistant.controller.responses;

import yte.intern.spring.application.assistant.entity.Assistant;

public record AssistantQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public AssistantQueryModel(Assistant assistant) {
        this(
                assistant.getId(),
                assistant.getName(),
                assistant.getSurname(),
                assistant.getEmail(),
                assistant.getUsername(),
                assistant.getPassword()
        );
    }
}
