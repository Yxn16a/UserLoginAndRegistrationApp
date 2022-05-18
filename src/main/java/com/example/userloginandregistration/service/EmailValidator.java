package com.example.userloginandregistration.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO: Regex to validata email  please search for this to validata email
        return true;
    }
}
