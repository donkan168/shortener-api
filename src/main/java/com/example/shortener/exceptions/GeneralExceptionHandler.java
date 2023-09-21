package com.example.shortener.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidHandler(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        String mensaje = "Argumento invalido";
        Optional<ObjectError> error = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .findFirst();
        if (error.isPresent()) {
            mensaje = error.get().getDefaultMessage();
        }

        log.info(methodArgumentNotValidException.getMessage());

        return ResponseEntity.badRequest().body(ExceptionDTO.builder().message(mensaje).build());
    }

    @ExceptionHandler(value = {BussinessException.class})
    public ResponseEntity<ExceptionDTO> negocioExceptionHandler(
            BussinessException negocioException) {

        log.error(negocioException.getMessage());

        return ResponseEntity.status(negocioException.getHttpStatusCode())
                .body(ExceptionDTO.builder()
                        .code(negocioException.getExceptionCode())
                        .message(negocioException.getMessage())
                        .build());
    }
}
