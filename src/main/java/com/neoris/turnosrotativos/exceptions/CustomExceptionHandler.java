package com.neoris.turnosrotativos.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// Controller de las las excepciones y sus codigos de error se definierion 3 para poder controlar mejor las excepcioens solicitadas como fueron:
// Metodos handlBussinesException para el codigo 409 (CONFLICT ),
// foundBussinesException para el codigo 404 (NOT_FOUND),
// BadRequestException para el codigo 400 (BAD_REQUEST)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map< String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        responseBody.put("errors", errors);

        return new ResponseEntity<>(responseBody, headers, status);


    }


    @ExceptionHandler (BussinesException.class)
    public ResponseEntity<Object> handlBussinesException (
            BussinesException ex, WebRequest request
    ){
        Map< String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("message", ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);

    }

    @ExceptionHandler (FoundException.class)
    public ResponseEntity<Object> foundBussinesException (
            FoundException ex, WebRequest request
    ){
        Map< String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("message", ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler (BadRequestException.class)
    public ResponseEntity<Object> BadRequestException (
            BadRequestException ex, WebRequest request
    ){
        Map< String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("message", ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

    }
}
