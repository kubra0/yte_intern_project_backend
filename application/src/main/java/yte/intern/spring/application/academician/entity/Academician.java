package yte.intern.spring.application.academician.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.application.assistant.entity.Assistant;
import yte.intern.spring.application.common.entity.BaseEntity;
import yte.intern.spring.application.lessons.entity.Lessons;
import yte.intern.spring.application.student.entity.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Academician extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    public Academician(String name,
                       String surname,
                       String email,
                       String username,
                       String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void update(Academician updatedAcademician) {
        this.name = updatedAcademician.name;
        this.surname = updatedAcademician.surname;
        this.email = updatedAcademician.email;
        this.password= updatedAcademician.password;
        this.username = updatedAcademician.username;
    }

    @OneToMany(mappedBy = "academician")
    private Set<Lessons> lessons= new HashSet<>();

    @OneToMany(mappedBy = "academician",cascade= CascadeType.ALL)
    private Set<Assistant> assistants= new HashSet<>();
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Set<Student> students= new HashSet<>();

}
