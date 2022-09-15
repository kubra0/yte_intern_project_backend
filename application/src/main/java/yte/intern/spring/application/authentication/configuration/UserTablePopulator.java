package yte.intern.spring.application.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import yte.intern.spring.application.authentication.entity.Authority;
import yte.intern.spring.application.authentication.entity.Users;
import yte.intern.spring.application.authentication.repository.AuthorityRepository;
import yte.intern.spring.application.authentication.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserTablePopulator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @PostConstruct
    public void populateDatabase() {
        if(!authorityRepository.existsByAuthority("STUDENT")){
            authorityRepository.save(new Authority("STUDENT"));
        }
        if(!authorityRepository.existsByAuthority("ASSISTANT")){
            authorityRepository.save(new Authority("ASSISTANT"));
        }
        if(!authorityRepository.existsByAuthority("ACADEMICIAN")) {
            authorityRepository.save(new Authority("ACADEMICIAN"));
        }
        if(!authorityRepository.existsByAuthority("ADMIN")) {
            authorityRepository.save(new Authority("ADMIN"));
        }
        Authority admin = authorityRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ADMIN not found!"));
        if (!userRepository.existsByUsername("admin")) {
            Users user = new Users(List.of(admin),"admin", passwordEncoder.encode("admin") );
            userRepository.save(user);
        }
    }
}