package yte.intern.spring.application.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.admin.entitiy.Admin;
import yte.intern.spring.application.admin.repository.AdminRepository;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;
import yte.intern.spring.application.student.entity.Student;
import yte.intern.spring.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public MessageResponse addAdmin(Admin admin) {
        adminRepository.save(admin);

        return new MessageResponse(ResponseType.SUCCESS, "Admin has been added successfully");
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
    }

    public MessageResponse deleteAdminById(Long id) {
        adminRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Admin has been deleted successfully");
    }

    public MessageResponse updateAdmin(Long id, Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        admin.update(updatedAdmin);

        adminRepository.save(admin);

        return new MessageResponse(ResponseType.SUCCESS, "Admin has been updated successfully");
    }
}
