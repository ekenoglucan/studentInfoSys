package com.example.demo.Response;

import com.example.demo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String userMail;
    private String password;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userMail = user.getUserMail();
        this.password = user.getPassword();
    }
}
