package org.garvk.user_service.advice;

import org.apache.coyote.Response;
import org.garvk.user_service.exception.InvalidCredentialsException;
import org.garvk.user_service.exception.UserNotFoundException;
import org.garvk.user_service.exception.UsernameTakenException;
import org.garvk.user_service.model.ErrorResponseDTO;
import org.hibernate.metamodel.internal.EmbeddableRepresentationStrategyPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCred(Exception ex, WebRequest req){
        return createErrorResponse(ex, HttpStatus.UNAUTHORIZED, req);
    }

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNameTaken(Exception ex, WebRequest req){
        return createErrorResponse(ex, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(Exception ex, WebRequest req){
        return createErrorResponse(ex, HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest req){
        return createErrorResponse(
                new Exception("Unexpected Error Occured"), HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    private ResponseEntity<ErrorResponseDTO> createErrorResponse(
            Exception aInException,
            HttpStatus aInHttpStatus,
            WebRequest aInRequest){

        String path = ((ServletWebRequest) aInRequest).getRequest().getRequestURI();

        ErrorResponseDTO aInOutErrorResponseDto = new ErrorResponseDTO(
                LocalDateTime.now().toString(),
                aInHttpStatus.value(),
                aInHttpStatus.getReasonPhrase(),
                aInException.getMessage(),
                path,
                "USER-SERVICE"
        );

        return new ResponseEntity<>(aInOutErrorResponseDto, aInHttpStatus);
    }

}
