package com.example.demo.Controller;

import com.example.demo.Response.UserResponse;
import com.example.demo.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/me")
    public UserResponse getUser() {
        return userService.getUser(userService.getAuthenticatedUserId());
    }

}
