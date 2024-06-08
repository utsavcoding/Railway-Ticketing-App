package com.rail.app.service;

import com.github.f4b6a3.ulid.UlidCreator;

public class Utils {

    public static String randomULIDGenerator(){
        return UlidCreator.getUlid().toString();
    }
}
