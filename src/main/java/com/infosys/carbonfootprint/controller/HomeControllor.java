package com.infosys.carbonfootprint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllor {
    @GetMapping("/")
    public String showmessage(){
        return"Hello SpringBoot";
    }
}
