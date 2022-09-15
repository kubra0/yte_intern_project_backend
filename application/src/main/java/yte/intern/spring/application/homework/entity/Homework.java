package yte.intern.spring.application.homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.assistant.entity.Assistant;
import yte.intern.spring.application.common.entity.BaseEntity;
import yte.intern.spring.application.lessons.entity.Lessons;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Homework extends BaseEntity {

    private String defination;
    private String file;
    private String time;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assistant_id", referencedColumnName = "ID")
    private Assistant assistant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lesson_id", referencedColumnName = "ID")
    private Lessons lessons;

    public Homework(String defination, String file, String time, Long assistant_id, Long lesson_id) {
        this.defination = defination;
        this.file = file;
        this.time = time;

        this.lessons=new Lessons();
        this.lessons.setId(lesson_id);

        this.assistant=new Assistant();
        this.assistant.setId(assistant_id);

    }


    public void update(Homework updateHomework) {
        this.defination = updateHomework.defination;
        this.file = updateHomework.file;
        this.time = updateHomework.time;
        this.lessons = updateHomework.lessons;
        this.assistant = updateHomework.assistant;
    }
}
