package yte.intern.spring.application.lessons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.lessons.entity.Lessons;

public interface LessonsRepository extends JpaRepository<Lessons, Long> {
}
