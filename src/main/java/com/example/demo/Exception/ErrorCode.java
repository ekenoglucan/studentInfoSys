package com.example.demo.Exception;

public enum ErrorCode {
    unknown(400),
    unauthorized(401),
    account_already_exists(403),
    account_missing(404),
    password_mismatch(409),
    resource_missing(404);

    private final int httpCode;

    ErrorCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() { return httpCode; }
}
