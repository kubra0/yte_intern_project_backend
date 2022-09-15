package yte.intern.spring.application.academician.controller.responses;

import yte.intern.spring.application.academician.entity.Academician;

public record AcademicianQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public AcademicianQueryModel(Academician academician) {
        this(
                academician.getId(),
                academician.getName(),
                academician.getSurname(),
                academician.getEmail(),
                academician.getUsername(),
                academician.getPassword()
        );
    }
}
