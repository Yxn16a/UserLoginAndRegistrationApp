package com.example.userloginandregistration.service;

import com.example.userloginandregistration.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final static String User_Not_Found = "user with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) {
        return appUserRepository.findByEmai(email).
                orElseThrow(()-> new UsernameNotFoundException(String.format(User_Not_Found,email)));
    }
}
