package yte.intern.spring.application.student.controller.responses;

import yte.intern.spring.application.student.entity.Student;

public record StudentQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public StudentQueryModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getUsername(),
                student.getPassword()
        );
    }
}

