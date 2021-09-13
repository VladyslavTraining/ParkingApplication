package com.delphi.nice.training.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.delphi.nice.training.security.UserPermission.*;
import static com.delphi.nice.training.security.UserRole.ADMIN;
import static com.delphi.nice.training.security.UserRole.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(TICKET_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/admin/**").hasAuthority(TICKET_READ_ALL.getPermission())
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority(TICKET_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority(TICKET_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails zakharAdmin = User.builder()
                .username("Zakhar")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails vladUser = User.builder()
                .username("Vlad")
                .password(passwordEncoder.encode("password"))
//                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(zakharAdmin, vladUser);
    }
}
