package com.example.userloginandregistration.repository;


import com.example.userloginandregistration.appuser.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
    Optional<AppUser> findByEmai(String email);
}
