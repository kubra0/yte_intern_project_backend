package yte.intern.spring.application.academician.controller.request;

import yte.intern.spring.application.academician.entity.Academician;

public record UpdateAcademicianRequest(
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public Academician toDomainEntity() {
        return new Academician(name, surname, email, username, password);
    }

}
