package com.delphi.nice.training.auth;

import com.delphi.nice.training.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

//@Entity
//@EqualsAndHashCode
//@NoArgsConstructor
@ToString
public class User implements UserDetails {

//    @Id
//    @SequenceGenerator(name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "user_sequence")
//    private long id;
    private String username;
    private String password;
//    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public User(String username,
                String password,
                UserRole userRole,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
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
        System.out.println(userRole.name());
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+userRole.name()));
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
