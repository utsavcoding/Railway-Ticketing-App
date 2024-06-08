package com.rail.app.db;

import com.rail.app.dto.User;

import java.util.HashMap;
import java.util.Map;

public class UserTable {

    private static final Map<String, User> table=new HashMap<>();

    public void write(User user){
        table.put(user.getEmail(),user);
    }

    public User read(String email){
        return table.get(email);
    }
}
