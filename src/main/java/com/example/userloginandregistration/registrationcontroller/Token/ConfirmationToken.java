package com.example.userloginandregistration.registrationcontroller.Token;

import com.example.userloginandregistration.domain.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence")
    @SequenceGenerator(name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1)
   // @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id",
                nullable = false) // many tokens can be given to the user
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
