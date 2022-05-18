package com.example.userloginandregistration.registrationcontroller;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrationRequest {
    // we do this when the request comes in
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

}
