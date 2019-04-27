package lz.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lz.exception.CardException;
import lz.exception.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(CardException ex, WebRequest request) {
	final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}