package yte.intern.spring.application.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.spring.application.authentication.controller.requests.LoginRequest;
import yte.intern.spring.application.authentication.service.LoginService;
import yte.intern.spring.application.common.response.AuthenticationResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}
