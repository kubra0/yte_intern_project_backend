package yte.intern.spring.application.lessons.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.common.entity.BaseEntity;
import yte.intern.spring.application.student.entity.Student;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lessons extends BaseEntity {
    private String isim;
    private String tanim;
    private String tip;
    private String kod;
    private String timeSlot;

    public Lessons(String isim,
                   String tanim,
                   String tip,
                   String kod,
                   String timeSlot,
                   Long academician_id
                   ) {
        this.isim = isim;
        this.tanim = tanim;
        this.tip = tip;
        this.kod = kod;
        this.timeSlot = timeSlot;
        this.academician = new Academician();
        this.academician.setId(academician_id);
    }

    public void update(Lessons updatedLesson) {
        this.isim = updatedLesson.isim;
        this.tanim = updatedLesson.tanim;
        this.tip = updatedLesson.tip;
        this.kod = updatedLesson.kod;
        this.timeSlot = updatedLesson.timeSlot;
        this.academician = updatedLesson.academician;
    }

    @ManyToOne(fetch = FetchType.LAZY) // ihtiyaç halinde dğier entityi yüklemeyi sağlar
    @JoinColumn(name="academician_id",referencedColumnName = "ID")
    private Academician academician;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Set<Student> students= new HashSet<>(); //Set sırasız depolama
}
