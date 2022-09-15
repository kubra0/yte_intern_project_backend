package yte.intern.spring.application.lessons.controller.request;

import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.admin.entitiy.Admin;
import yte.intern.spring.application.lessons.entity.Lessons;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public record AddLessonsRequest(
        @NotBlank
        String isim,
        @NotBlank
        String tanim,
        @NotBlank
        String tip,
        @NotBlank
        String kod,
        String timeSlot,
        Long academician_id


) {

    public Lessons toDomainEntity() {
        return new Lessons(isim,tanim,tip,kod,timeSlot,academician_id);
    }
}
