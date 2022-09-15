package yte.intern.spring.application.student.controller.request;

import yte.intern.spring.application.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public Student toDomainEntity() {
        return new Student(name, surname, email, username, password);
    }
}
