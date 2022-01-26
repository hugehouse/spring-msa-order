package com.msa.order.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 존재하지 않는 페이지 접근
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorHolder> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorHolder(ErrorResponse.NotFoundContent, e.getLocalizedMessage()));
    }

    // 다른 서비스로부터 받아온 정보 처리
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException e) {
        return ResponseEntity.status(e.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getResponseBodyAsString());
    }

    // Service단에서의 Vaildation 에러 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        getConstraintViolationResultMessage(e.getConstraintViolations().iterator())));
    }

    // Controller단에서의 Validation 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        getMethodArgumentNotValidResultMessage(e.getAllErrors().iterator())));
    }

    // 중복 제거해야 함
    private String getConstraintViolationResultMessage(Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(getPopertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                    .append("'. ")
                    .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }
        return resultMessageBuilder.toString();
    }

    private String getMethodArgumentNotValidResultMessage(Iterator<ObjectError> objectErrorIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (objectErrorIterator.hasNext()) {
            ObjectError objectError = objectErrorIterator.next();
            FieldError error = (FieldError)objectError;
            resultMessageBuilder
                    .append("['")
                    .append(getPopertyName(error.getField())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(error.getRejectedValue()) // 유효하지 않은 값
                    .append("'. ")
                    .append(error.getDefaultMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (objectErrorIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }
        return resultMessageBuilder.toString();
    }

    private String getPopertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }
}
