package yte.intern.spring.application.common.response;

public record AuthenticationResponse(
        ResponseType responseType,
        String message,
        String Role
) {
}
