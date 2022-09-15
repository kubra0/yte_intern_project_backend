package yte.intern.spring.application.common.response;

public record MessageResponse(
        ResponseType responseType,
        String message
) {
}
