package yte.intern.spring.application.assistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.assistant.entity.Assistant;
import yte.intern.spring.application.assistant.repository.AssistantRepository;
import yte.intern.spring.application.common.response.MessageResponse;
import yte.intern.spring.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistantService {
    private final AssistantRepository assistantRepository;

    public MessageResponse addAssistant(Assistant assistant) {
        assistantRepository.save(assistant);

        return new MessageResponse(ResponseType.SUCCESS, "Assistatn has been added successfully");
    }

    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }

    public Assistant getById(Long id) {
        return assistantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assistant not found"));
    }

    public MessageResponse deleteAssistantById(Long id) {
        assistantRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Assistant has been deleted successfully");
    }

    public MessageResponse updateAssistant(Long id, Assistant updatedAssistant) {
        Assistant assistant = assistantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assistant not found"));

        assistant.update(updatedAssistant);

        assistantRepository.save(assistant);

        return new MessageResponse(ResponseType.SUCCESS, "Assistant has been updated successfully");
    }
}
