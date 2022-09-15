package yte.intern.spring.application.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.homework.entity.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
