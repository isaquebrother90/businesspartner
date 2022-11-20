package br.com.businesspart.app.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "br.com.businesspart.app.controllers")
public class PartnerControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PartnerNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> partnerNotFound(PartnerNotFoundException partnerNotFound) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Partner not found!");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> handleAnyException(Exception e) {

        String errorDescription = e.getMessage();
        if (errorDescription == null) errorDescription = e.toString();

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred:" + errorDescription);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValid) {

        BindingResult result = notValid.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder("The following fields cannot be null: ");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" | ");
            sb.append(" -> ");
            sb.append(fieldError.getField());
            sb.append(" <- ");
        }

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
