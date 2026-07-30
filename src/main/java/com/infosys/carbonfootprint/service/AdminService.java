package com.infosys.carbonfootprint.service;

import com.infosys.carbonfootprint.dto.PendingUserDto;
import com.infosys.carbonfootprint.dto.UserDetailsToAdminDto;
import com.infosys.carbonfootprint.dto.UserListDto;
import com.infosys.carbonfootprint.entity.UserGovernmentDocument;

import java.util.List;

public interface AdminService {

    List<PendingUserDto> getPendingUsers();

    UserDetailsToAdminDto getUserDetails(Long id);

    String approveUser(Long id);

    String rejectUser(Long id);

    List<PendingUserDto> getApprovedUsers();

    List<PendingUserDto> getRejectedUsers();
    List<UserListDto> getAllUsers();

    UserGovernmentDocument getGovernmentDocument(Long userId);
}
