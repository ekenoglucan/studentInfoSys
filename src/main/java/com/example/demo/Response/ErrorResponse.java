package com.example.demo.Response;

import com.example.demo.Exception.ErrorCode;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private Date timestamp;
    private int statusCode;
    private ErrorCode errorCode;
    private String message;
}
