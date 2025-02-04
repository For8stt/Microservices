package com.example.demo.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/open")
    public String open(){
        return "OPEN";
    }

    @GetMapping("/closed")
    public String closed(){
        return "CLOSED";
    }
    @GetMapping("/special")
    @PreAuthorize("hasRole('superuser')")
    public String special(){
        return "SPECIAL";
    }
    @GetMapping("/basic")
    @PreAuthorize("hasRole('superusr') or hasRole('basicuser')")
    public String basic(){
        return "BASIC";
    }
}
