package com.infosys.carbonfootprint.controller;

import com.infosys.carbonfootprint.dto.AdminStatsDto;
import com.infosys.carbonfootprint.dto.PendingUserDto;
import com.infosys.carbonfootprint.dto.UserDetailsToAdminDto;
import com.infosys.carbonfootprint.dto.UserListDto;
import com.infosys.carbonfootprint.service.AdminService;
import com.infosys.carbonfootprint.service.AdminStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminStatsService adminStatsService;
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Dashboard Test";
    }
    @GetMapping("/all-users")
    public List<UserListDto> getAllUsers() {
        return adminService.getAllUsers();
    }
    @GetMapping("/pending-users")
    public List<PendingUserDto> getPendingUsers(){
        return adminService.getPendingUsers();
    }

    @GetMapping("/user-details/{id}")
    public UserDetailsToAdminDto getUserDetails(
        @PathVariable Long id){
        return adminService.getUserDetails(id);
    }

    @PostMapping("/approve-user/{id}")
    public String approveUser(
        @PathVariable Long id){
        return adminService.approveUser(id);
    }

    @PostMapping("/reject-user/{id}")
    public String rejectUser(
        @PathVariable Long id){
        return adminService.rejectUser(id);
    }

    @GetMapping("/approved-users")
    public List<PendingUserDto> getApprovedUsers(){
        return adminService.getApprovedUsers();
    }

    @GetMapping("/rejected-users")
    public List<PendingUserDto> getRejectedUsers(){
        return adminService.getRejectedUsers();
    }

    @GetMapping("/statistics")
    public AdminStatsDto getStatistics(){
        return adminStatsService.getStatistics();
    }

    @PostMapping("/logout")
    public String logout(){
        return "Admin Logout Successfully";
    }
}
