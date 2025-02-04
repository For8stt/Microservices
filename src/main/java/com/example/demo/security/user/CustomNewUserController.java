package com.example.demo.security.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomNewUserController {
    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder encoder;
    public CustomNewUserController(CustomUserRepository customUserRepository,
                                   PasswordEncoder encoder){
        this.customUserRepository=customUserRepository;
        this.encoder=encoder;
    }
    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser){
        Optional<CustomUser> optionalUser=customUserRepository.findById(customUser.getUsername());

        if(!optionalUser.isPresent()){
            customUserRepository.save(new CustomUser(customUser.getUsername(),encoder.encode(customUser.getPassword())));
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.ok("Failure");

    }



}
