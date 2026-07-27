package com.infosys.carbonfootprint.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.carbonfootprint.entity.User;
import com.infosys.carbonfootprint.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/pending-users")
    public ResponseEntity<List<User>> getPendingUsers() {

        return ResponseEntity.ok(
            userService.getPendingUsers()
        );

    }

    @PatchMapping("/users/{userId}/approve")
    public ResponseEntity<String> approveUser(
        @PathVariable Long userId) {

        return ResponseEntity.ok(
            userService.approveUser(userId)
        );
    }

    @PatchMapping("/users/{userId}/reject")
    public ResponseEntity<String> rejectUser(
        @PathVariable Long userId) {

        return ResponseEntity.ok(
            userService.rejectUser(userId)
        );
    }
}
