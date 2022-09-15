package yte.intern.spring.application.student.controller.request;

import yte.intern.spring.application.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddStudentRequest(
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
    public Student toDomainEntity() {
        return new Student(name, surname, email, username, password);
    }
}
