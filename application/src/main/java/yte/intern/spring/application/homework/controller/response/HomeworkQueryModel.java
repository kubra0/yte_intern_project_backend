package yte.intern.spring.application.homework.controller.response;


import yte.intern.spring.application.homework.entity.Homework;

import java.io.File;

public record HomeworkQueryModel(
        Long id,
        String defination,
        String file,
        String time,
        Long lesson_id,
        Long assistant_id
        ) {
    public HomeworkQueryModel(Homework homework) {
        this(
                homework.getId(),
                homework.getDefination(),
                homework.getFile(),
                homework.getTime(),
                homework.getLessons().getId(),
                homework.getAssistant().getId()

        );
    }
}
