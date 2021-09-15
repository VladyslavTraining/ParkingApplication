package com.delphi.nice.training.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    USER(Sets.newHashSet(UserPermission.TICKET_READ, UserPermission.TICKET_WRITE)),
    ADMIN(Sets.newHashSet(UserPermission.TICKET_READ, UserPermission.TICKET_READ_ALL,UserPermission.TICKET_WRITE));
    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissionsSet = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissionsSet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsSet;
    }
}
