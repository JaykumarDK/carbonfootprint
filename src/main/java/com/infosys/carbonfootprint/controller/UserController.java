package com.infosys.carbonfootprint.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.carbonfootprint.dto.UserRegistrationDTO;
import com.infosys.carbonfootprint.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
        value = "/register",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> registerUser(
        @Valid @ModelAttribute UserRegistrationDTO userRegistrationDTO)
        throws IOException {

        String message = userService.registerUser(userRegistrationDTO);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(message);
    }
}
