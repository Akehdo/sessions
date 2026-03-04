package com.orderplatform.newproject.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;



    public static User create(String email, String password) {
        User user = new User();

        user.email = email;
        user.password = password;

        return user;
    }
}
