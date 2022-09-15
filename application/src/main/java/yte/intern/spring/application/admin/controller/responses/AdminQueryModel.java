package yte.intern.spring.application.admin.controller.responses;

import yte.intern.spring.application.admin.entitiy.Admin;
import yte.intern.spring.application.student.entity.Student;

public record AdminQueryModel(
        Long id,
        String username,
        String password
) {
    public AdminQueryModel(Admin admin) {
        this(
                admin.getId(),
                admin.getUsername(),
                admin.getPassword()
        );
    }
}
