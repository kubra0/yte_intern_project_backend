package yte.intern.spring.application.admin.controller.request;

import yte.intern.spring.application.admin.entitiy.Admin;

public record UpdateAdminRequest(
        String password
) {
    public Admin toDomainEntity() {
        return new Admin(null, password);
    }

}
