package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Exception.BusinessException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Request.ResetPasswordRequest;
import com.example.demo.Request.UserLoginRequest;
import com.example.demo.Request.UserRegisterRequest;
import com.example.demo.Response.UserAuthResponse;
import com.example.demo.Security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserAuthResponse login(@RequestBody UserLoginRequest loginRequest){
        User user = userRepository.findByUserMail(loginRequest.getEmail());

        if (user == null) {
            throw new BusinessException(ErrorCode.account_missing, "Email bulunamadı!");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.password_mismatch, "Şifre uyuşmuyor!");
        }
        return UserAuthResponse.builder()
                .id(user.getId())
                .token(jwtService.createToken(user.getId().toString()))
                .build();
    }

    public void register(@RequestBody UserRegisterRequest registerRequest){
        User existingUser = userRepository.findByUserMail(registerRequest.getUserMail());

        if (existingUser != null) {
            throw new BusinessException(ErrorCode.account_already_exists, "Email zaten bulunuyor!");
        }

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setUserMail(registerRequest.getUserMail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }

    public void resetPassword(Long userId, ResetPasswordRequest body) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.account_missing, "Kullanıcı Bulunamadı!"));
        System.out.println(userId);
        if (!passwordEncoder.matches(body.getOldPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.password_mismatch, "Hatalı Şifre!");
        }

        user.setPassword(passwordEncoder.encode(body.getNewPassword()));
        userRepository.save(user);
    }
}
