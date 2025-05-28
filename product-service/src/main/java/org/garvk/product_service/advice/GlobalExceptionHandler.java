package org.garvk.product_service.advice;

import org.garvk.product_service.exception.ProductNotFound;
import org.garvk.product_service.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFound(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    private ResponseEntity<ErrorResponseDTO> createResponse(Exception ex, HttpStatus status, WebRequest req){

        String path = ((ServletWebRequest) req).getRequest().getRequestURI();

        ErrorResponseDTO aOutErrorResponse = new ErrorResponseDTO(
                LocalDateTime.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path,
                "PRODUCT-SERVICE"
        );

        return new ResponseEntity<>(aOutErrorResponse, status);
    }

}
