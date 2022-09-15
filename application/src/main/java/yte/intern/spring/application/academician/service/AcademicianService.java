package yte.intern.spring.application.academician.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;
import yte.intern.spring.application.academician.entity.Academician;
import yte.intern.spring.application.academician.repository.AcademicianRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicianService {
    private final AcademicianRepository academicianRepository;

    public MessageResponse addAcademician(Academician academician) {
        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been added successfully");
    }

    public List<Academician> getAllAcademicians() {
        return academicianRepository.findAll();
    }

    public Academician getById(Long id) {
        return academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));
    }

    public MessageResponse deleteAcademicianById(Long id) {
        academicianRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been deleted successfully");
    }

    public MessageResponse updateAcademician(Long id, Academician updatedAcademician) {
        Academician academician = academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));

        academician.update(updatedAcademician);

        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been updated successfully");
    }
}
