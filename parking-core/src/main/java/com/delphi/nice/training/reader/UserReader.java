package com.delphi.nice.training.reader;

import com.delphi.nice.training.auth.User;
import com.delphi.nice.training.security.UserRole;
import com.google.common.collect.Lists;
import org.json.simple.JSONObject;
import org.springframework.security.core.GrantedAuthority;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UserReader implements Reader {
    @Override
    public List<User> getJsonArr(String filepath) {
        String username;
        String password;
        UserRole userRole;
        boolean isAccountNonExpired;
        boolean isAccountNonLocked;
        boolean isCredentialsNonExpired;
        boolean isEnabled;
        User user;
        List<User> list = new LinkedList<>();
        for (JSONObject object : new JSONReader().getJsonArr(filepath)) {
            username = (String) object.get("username");
            password = (String) object.get("password");
            userRole = object.containsValue("ROLE_ADMIN") ? UserRole.ADMIN : UserRole.USER;
            isAccountNonExpired = (boolean) object.get("isAccountNonExpired");
            isAccountNonLocked = (boolean) object.get("isAccountNonLocked");
            isCredentialsNonExpired = (boolean) object.get("isCredentialsNonExpired");
            isEnabled = (boolean) object.get("isEnabled");
            user = new User(1,username,
                    password,
                    userRole,
                    isAccountNonExpired,
                    isAccountNonLocked,
                    isCredentialsNonExpired,
                    isEnabled);
            list.add(user);
        }
        return list;
    }
}
