package com.example.testwork.exceptionhandler;

import com.example.testwork.exceptionhandler.exception.BusinessException;
import com.example.testwork.exceptionhandler.response.CommonExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        CommonExceptionResponse response = CommonExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .exceptionClass(ex.getClass().getSimpleName())
                .message(String.join("; ", errors))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CommonExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponseBody(e.getMessage(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonExceptionResponse> handleBusinessException(RuntimeException e) {
        return ResponseEntity.unprocessableEntity()
                .body(buildResponseBody(e.getMessage(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonExceptionResponse> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponseBody(e.getMessage(), e.getClass().getSimpleName()));
    }

    private CommonExceptionResponse buildResponseBody(String message, String exceptionClass) {
        return CommonExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .exceptionClass(exceptionClass)
                .message(message)
                .build();
    }
}
