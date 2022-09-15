package yte.intern.spring.application.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.admin.entitiy.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminById(Long id);

    List<Admin> findAll();
}
