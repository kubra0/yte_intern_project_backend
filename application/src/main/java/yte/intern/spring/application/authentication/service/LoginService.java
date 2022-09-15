package yte.intern.spring.application.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.intern.spring.application.authentication.controller.requests.LoginRequest;
import yte.intern.spring.application.authentication.repository.UserRepository;
import yte.intern.spring.application.common.response.AuthenticationResponse;
import yte.intern.spring.application.common.response.ResponseType;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public AuthenticationResponse login(LoginRequest loginRequest) {
        var preAuthentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authority = "";
        try {
            Authentication postAuthentication = authenticationManager.authenticate(preAuthentication);
            SecurityContext newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(postAuthentication);
            SecurityContextHolder.setContext(newContext);

            authority = newContext.getAuthentication().getAuthorities().stream().toList().get(0).getAuthority();

            return new AuthenticationResponse(ResponseType.SUCCESS, "Login is successful",authority);
        } catch (AuthenticationException e) {
            return new AuthenticationResponse(ResponseType.ERROR, "Authentication exception: %s".formatted(e.getMessage()), authority);
        }
    }
}
