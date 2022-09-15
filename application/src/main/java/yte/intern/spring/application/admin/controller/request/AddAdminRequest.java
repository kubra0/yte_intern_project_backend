package yte.intern.spring.application.admin.controller.request;

import yte.intern.spring.application.admin.entitiy.Admin;
import yte.intern.spring.application.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAdminRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {

    public Admin toDomainEntity() {
        return new Admin(username, password);
    }
}
