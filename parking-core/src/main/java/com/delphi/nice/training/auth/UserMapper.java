package com.delphi.nice.training.auth;


import com.delphi.nice.training.security.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(@NonNull ResultSet result, int i) throws SQLException {
        long id = result.getLong(1);
        String username = result.getString(2);
        String password = result.getString(3);
        UserRole userRole = result.getString(4).equals("ROLE_ADMIN") ? UserRole.ADMIN: UserRole.USER;
        boolean isAccountNonExpired = result.getBoolean(5);
        boolean isAccountNonLocked = result.getBoolean(6);
        boolean isCredentialsNonExpired = result.getBoolean(7);
        boolean isEnabled = result.getBoolean(8);
        return new User(
                id,
                username,
                password,
                userRole,
                isAccountNonExpired,
                isAccountNonLocked,
                isCredentialsNonExpired,
                isEnabled);
    }
}
