package com.ani.pm.exception;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ClientNotFoundException.class)
    ProblemDetail handleClientNotFound(ClientNotFoundException ex, WebRequest req) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Client Not Found");
        pd.setType(URI.create("https://api.ani.pm/errors/client-not-found"));
        return pd;
    }


    @ExceptionHandler(InvalidClientTypeException.class)
    ProblemDetail handleInvalidType(InvalidClientTypeException ex) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Invalid Client Type");
        pd.setType(URI.create("https://api.ani.pm/errors/invalid-client-type"));
        return pd;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArg(IllegalArgumentException ex) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Bad Request");
        pd.setType(URI.create("https://api.ani.pm/errors/bad-request"));
        return pd;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {


        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "Invalid value",
                        (a, b) -> a   // keep first message if duplicate field
                ));

        var pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "One or more fields failed validation"
        );
        pd.setTitle("Validation Failed");
        pd.setType(URI.create("https://api.ani.pm/errors/validation-failed"));
        pd.setProperty("fieldErrors", fieldErrors);   // extra properties on ProblemDetail
        return ResponseEntity.unprocessableEntity().body(pd);
    }


    @ExceptionHandler(Exception.class)
    ProblemDetail handleGeneric(Exception ex) {
        var pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred"
        );
        pd.setTitle("Internal Server Error");
        pd.setType(URI.create("https://api.ani.pm/errors/internal"));
        return pd;
    }
}
