package com.infosys.carbonfootprint.serviceimpl;

import com.infosys.carbonfootprint.config.JwtUtil;
import com.infosys.carbonfootprint.dto.PasswordResetDTO;
import com.infosys.carbonfootprint.exception.UnauthorizedAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infosys.carbonfootprint.dto.LoginRequestDTO;
import com.infosys.carbonfootprint.dto.LoginResponseDTO;
import com.infosys.carbonfootprint.entity.User;
import com.infosys.carbonfootprint.repository.UserRepository;
import com.infosys.carbonfootprint.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository
                .findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid username or password"
                        )
                );

        if (!"APPROVED".equals(user.getStatus())) {
            throw new IllegalArgumentException(
                    "User account is not approved"
            );
        }

        String generatedHash = passwordEncoder.encode(loginRequestDTO.getPassword());

        boolean passwordMatches =
                passwordEncoder.matches(
                        loginRequestDTO.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new UnauthorizedAccessException(
                    "Invalid username or password"
            );
        }

        String message;

        if (Boolean.TRUE.equals(user.getFirstLogin())) {
            message = "Login successful. Password reset required.";
        } else {
            message = "Login successful.";
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getFirstLogin(),
                token,
                message
        );
    }

    @Override
    public String restPassword(PasswordResetDTO passwordResetDTO) {
        return "";
    }

    @Override
    public String resetPassword(PasswordResetDTO dto) {

        User user = userRepository
            .findByUsername(dto.getUsername())
            .orElseThrow(() ->
                new IllegalArgumentException("User not found")
            );

        if (!"APPROVED".equals(user.getStatus())) {
            throw new IllegalArgumentException(
                "User account is not approved"
            );
        }

        if (!Boolean.TRUE.equals(user.getFirstLogin())) {
            throw new IllegalArgumentException(
                "Password reset is not required"
            );
        }

        if (!passwordEncoder.matches(
            dto.getCurrentPassword(),
            user.getPassword())) {

            throw new IllegalArgumentException(
                "Current password is incorrect"
            );
        }

        if (!dto.getNewPassword()
            .equals(dto.getConfirmPassword())) {

            throw new IllegalArgumentException(
                "New password and confirm password do not match"
            );
        }

        if (passwordEncoder.matches(
            dto.getNewPassword(),
            user.getPassword())) {

            throw new IllegalArgumentException(
                "New password must be different from current password"
            );
        }

        String encodedPassword =
            passwordEncoder.encode(dto.getNewPassword());

        user.setPassword(encodedPassword);
        user.setFirstLogin(false);

        userRepository.save(user);

        return "Password reset successfully";
    }
}
