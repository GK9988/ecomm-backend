package org.garvk.payment_service.advice;

import org.garvk.payment_service.Exception.PaymentCredPresent;
import org.garvk.payment_service.Exception.PaymentCredsNotFound;
import org.garvk.payment_service.Exception.PaymentInvalidCred;
import org.garvk.payment_service.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentCredPresent.class)
    public ResponseEntity<ErrorResponseDTO> handlePaymentCredPresent(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    @ExceptionHandler(PaymentCredsNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handlePaymentCredNotFound(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(PaymentInvalidCred.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPaymentCred(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllException(Exception ex, WebRequest req){
        return createResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    private ResponseEntity<ErrorResponseDTO> createResponse(
            Exception aInException,
            HttpStatus aInHttpStatus,
            WebRequest aInRequest
    ){
        String path = ((ServletWebRequest) aInRequest).getRequest().getRequestURI();

        ErrorResponseDTO aOutErrorResponse = new ErrorResponseDTO(
                LocalDateTime.now().toString(),
                aInHttpStatus.value(),
                aInHttpStatus.getReasonPhrase(),
                aInException.getMessage(),
                path,
                "PAYMENT-SERVICE"
        );

        return new ResponseEntity<>(aOutErrorResponse, aInHttpStatus);
    }

}
