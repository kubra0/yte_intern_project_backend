package yte.intern.spring.application.exam.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.common.entity.BaseEntity;
import yte.intern.spring.application.lessons.entity.Lessons;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam extends BaseEntity {
    private String name;
    private String time;
    private String room;
    private String info;

    public Exam(String name,
                String time,
                String room,
                String info,
                Long lesson_id
    ) {
        this.name = name;
        this.time = time;
        this.room = room;
        this.info = info;
        this.lessons = new Lessons();
        this.lessons.setId(lesson_id);

    }

    public void update(Exam updatedExam) {
        this.name = updatedExam.name;
        this.time = updatedExam.time;
        this.room = updatedExam.room;
        this.info= updatedExam.info;
        this.lessons = updatedExam.lessons;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lesson_id",referencedColumnName = "ID")
    private Lessons lessons;

}
