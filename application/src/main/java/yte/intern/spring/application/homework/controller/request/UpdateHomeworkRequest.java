package yte.intern.spring.application.homework.controller.request;


import yte.intern.spring.application.homework.entity.Homework;


public record UpdateHomeworkRequest(
        String defination,
        String file,
        String time,
        Long lesson_id,
        Long assistant_id
) {
    public Homework toDomainEntity() {
        return new Homework(defination, file, time, lesson_id, assistant_id);
    }
}
