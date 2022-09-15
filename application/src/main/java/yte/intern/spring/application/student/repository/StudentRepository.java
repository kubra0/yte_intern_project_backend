package yte.intern.spring.application.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentById(Long id);

    List<Student> findAll();
}
