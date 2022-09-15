package yte.intern.spring.application.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.exam.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
