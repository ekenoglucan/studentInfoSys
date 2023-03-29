package com.example.demo.Request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class UserLoginRequest {
    @Email(message = "Geçersiz email!")
    @NotEmpty(message = "Email boş olamaz!")
    private String email;

    @NotEmpty(message = "Şifre boş olamaz!")
    private String password;
}
