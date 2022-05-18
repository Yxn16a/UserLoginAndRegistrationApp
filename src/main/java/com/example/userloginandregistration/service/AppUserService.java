package com.example.userloginandregistration.service;

import com.example.userloginandregistration.domain.AppUser;
import com.example.userloginandregistration.registrationcontroller.Token.ConfirmationToken;
import com.example.userloginandregistration.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mapping.model.AbstractPersistentProperty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final static String User_Not_Found = "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) {
        return appUserRepository.findByEmail(email).
                orElseThrow(()-> new UsernameNotFoundException(String.format(User_Not_Found, email)));
    }
    // return the link for confirmation
    public String signUppUser(AppUser appUser){
      boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).
              isPresent();
      if(userExists){
          throw  new IllegalStateException("email already taken");
      }
      String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
      appUser.setPassword(encodedPassword);
      appUserRepository.save(appUser);

        String  token =UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.SaveConfirmationToken(confirmationToken);
        // TODO SEND EMAIL

        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}

