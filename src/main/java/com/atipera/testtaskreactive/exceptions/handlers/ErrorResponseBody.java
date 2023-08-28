package com.atipera.testtaskreactive.exceptions.handlers;


import com.atipera.testtaskreactive.exceptions.ExceptionFrame;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponseBody {
    private final String message;
    private final Integer status;

    public ErrorResponseBody(ExceptionFrame exception) {
        this.message = exception.getMessage();
        this.status = exception.getStatusCode().value();
    }

    @JsonCreator
    public ErrorResponseBody(@JsonProperty("status") Integer status,
                             @JsonProperty("message") String message) {
        this.status = status;
        this.message = message;
    }
}
