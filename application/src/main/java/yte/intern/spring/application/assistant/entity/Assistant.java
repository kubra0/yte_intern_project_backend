package yte.intern.spring.application.assistant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.common.entity.BaseEntity;
import yte.intern.spring.application.homework.entity.Homework;
import yte.intern.spring.application.lessons.entity.Lessons;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Assistant extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    public Assistant(String name,
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

    public void update(Assistant updatedAssistant) {
        this.name = updatedAssistant.name;
        this.surname = updatedAssistant.surname;
        this.username = updatedAssistant.username;
        this.email = updatedAssistant.email;
        this.password= updatedAssistant.password;
    }
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="academician_id",referencedColumnName = "ID")
    private Academician academician;

}
