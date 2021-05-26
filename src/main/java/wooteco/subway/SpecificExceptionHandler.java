package wooteco.subway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wooteco.subway.auth.exception.AuthorizationException;
import wooteco.subway.exception.DomainRelatedException;
import wooteco.subway.exception.ValidationFailureException;
import wooteco.subway.exception.dto.ErrorResponse;

@ControllerAdvice
public class SpecificExceptionHandler {

    @ExceptionHandler({DomainRelatedException.class, ValidationFailureException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationFailure(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(e.getMessage()));
    }
}
