package com.example.demo.security.user;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "custom_user")
@Data
public class CustomUser {
    @Id
    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;


    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public CustomUser(){}
}
