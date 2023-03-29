package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Exception.BusinessException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.unauthorized, "Yeterli yetki bulunmuyor!"));

        return new UserResponse(user);
    }

    public Long getAuthenticatedUserId() {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")) {
            throw new BusinessException(ErrorCode.unauthorized, "Yeterli yetki bulunmuyor!");
        }
        return Long.parseLong(principal);
    }
}
