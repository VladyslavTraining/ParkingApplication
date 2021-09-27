package com.delphi.nice.training.auth;

import com.delphi.nice.training.security.UserRole;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

//@Entity
//@EqualsAndHashCode
//@NoArgsConstructor
@ToString
@NoArgsConstructor
public class User implements UserDetails {

//    @Id
//    @SequenceGenerator(name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "user_sequence")
    private long id;
    private String username;
    private String password;
//    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User(long id,
            String username,
                String password,
                UserRole userRole,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.userRole = userRole;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+userRole));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
