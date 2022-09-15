package yte.intern.spring.application.exam.controller.response;

import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.exam.entity.Exam;

public record ExamQueryModel(
        Long id,
        String name,
        String time,
        String room,
        String info,
        Long lesson_id
        ) {
    public ExamQueryModel(Exam exam) {
        this(
                exam.getId(),
                exam.getName(),
                exam.getTime(),
                exam.getRoom(),
                exam.getInfo(),
                exam.getLessons().getId()
        );
    }
}


