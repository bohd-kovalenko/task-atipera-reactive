package com.atipera.testtaskreactive.exceptions.handlers;

import com.atipera.testtaskreactive.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class MainExceptionHandler extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorResponseBody> handleUserNotFoundException(UserNotFoundException e) {
        log.error(String.format("Error message: %s, response status: %d ",
                e.getMessage(), e.getStatusCode().value()));
        return new ResponseEntity<>(new ErrorResponseBody(e), e.getStatusCode());
    }

    @Override
    @SneakyThrows
    protected Mono<ResponseEntity<Object>> handleNotAcceptableStatusException(NotAcceptableStatusException ex,
                                                                              HttpHeaders headers,
                                                                              HttpStatusCode status,
                                                                              ServerWebExchange exchange) {
        log.error(String.format("Error message: %s, response status: %d ",
                ex, ex.getStatusCode().value()));
        return Mono.just(new ResponseEntity<>(objectMapper.writeValueAsString(new ErrorResponseBody(ex.getStatusCode().value(), ex.getMessage()))
                , ex.getStatusCode()));
    }
}
