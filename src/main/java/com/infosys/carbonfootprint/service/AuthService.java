package com.infosys.carbonfootprint.service;

import com.infosys.carbonfootprint.dto.LoginRequestDTO;
import com.infosys.carbonfootprint.dto.LoginResponseDTO;
import com.infosys.carbonfootprint.dto.PasswordResetDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    String restPassword(PasswordResetDTO passwordResetDTO);

    String resetPassword(PasswordResetDTO dto);
}
