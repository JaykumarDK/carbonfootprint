package com.infosys.carbonfootprint.service;

import java.io.IOException;
import java.util.List;

import com.infosys.carbonfootprint.dto.UserRegistrationDTO;
import com.infosys.carbonfootprint.entity.User;

public interface UserService {

    String registerUser(UserRegistrationDTO userRegistrationDTO) throws IOException;
    //String registerUser(UserRegistrationDTO userRegistrationDTO) throws IOException;

    List<User> getPendingUsers();

    String approveUser(Long userId);

    String rejectUser(Long userId);
}
