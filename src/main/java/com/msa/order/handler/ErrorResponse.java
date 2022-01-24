package com.msa.order.handler;

public enum ErrorResponse {
    NotFoundContent(4001, "Not found content"),
    InsertConstraintViolation(4002, "Violated constraint");

    private int code;
    private String description;

    private ErrorResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
