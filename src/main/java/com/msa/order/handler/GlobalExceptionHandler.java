package com.msa.order.handler;

import com.msa.order.handler.message.ConstraintViolationMessageBuilder;
import com.msa.order.handler.message.ExceptionResultMessageBuilder;
import com.msa.order.handler.message.MethodArgumentNotValidMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;

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

    /*
    Validation Exception 두 메소드에 Strategy 패턴 적용해서 출력 방식 통일했음
    ExceptionMessageProvider 인터페이스로 확장한 두 클래스를 ConstraintViolationMessageBuilder에서 주입받아 사용
     */

    // Service단에서의 Vaildation 에러 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {

        ExceptionResultMessageBuilder builder = new ExceptionResultMessageBuilder(
                new ConstraintViolationMessageBuilder(e.getConstraintViolations().iterator()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        builder.getResultMessage()));
    }

    // Controller단에서의 Validation 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ExceptionResultMessageBuilder builder = new ExceptionResultMessageBuilder(
                new MethodArgumentNotValidMessageBuilder(e.getAllErrors().iterator()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHolder(ErrorResponse.InsertConstraintViolation,
                        builder.getResultMessage()));
    }
}
