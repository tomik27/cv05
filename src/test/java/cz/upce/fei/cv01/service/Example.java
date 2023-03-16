package cz.upce.fei.cv01.service;

import cz.upce.fei.cv01.domain.AppUser;

import java.time.LocalDateTime;

public final class Example {

    public static AppUser EXISTING = new AppUser(100l,
            "test1","1234",true, LocalDateTime.now(),LocalDateTime.now());

    private Example(){

    }
}
