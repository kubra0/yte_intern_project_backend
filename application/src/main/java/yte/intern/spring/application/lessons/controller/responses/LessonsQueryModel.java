package yte.intern.spring.application.lessons.controller.responses;

import yte.intern.spring.application.lessons.entity.Lessons;

public record LessonsQueryModel(
        Long id,
        String isim,
        String tanim,
        String tip,
        String kod,
        String timeSlot,
        Long academician_id
) {
    public LessonsQueryModel(Lessons lessons) {
        this(
                lessons.getId(),
                lessons.getIsim(),
                lessons.getTanim(),
                lessons.getTip(),
                lessons.getKod(),
                lessons.getTimeSlot(),
                lessons.getAcademician().getId()
        );
    }
}
