package com.orderplatform.newproject.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "expired_at", nullable = false)
    private Instant expiredAt;

    @ManyToOne
    private User user;

    public static RefreshToken create(User user, String token, long expirationSeconds) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.user = user;
        refreshToken.token = token;
        refreshToken.expiredAt = Instant.now().plusSeconds(expirationSeconds);
        refreshToken.revoked = false;

        return  refreshToken;
    }
}
