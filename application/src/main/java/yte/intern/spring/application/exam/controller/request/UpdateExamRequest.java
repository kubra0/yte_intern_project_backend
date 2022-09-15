package yte.intern.spring.application.exam.controller.request;

import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.exam.entity.Exam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UpdateExamRequest(

        String name,
        String time,
        String room,
        String info,
        Long lesson_id
) {
    public Exam toDomainEntity() {
        return new Exam(name, time, room, info,lesson_id);
    }

}
