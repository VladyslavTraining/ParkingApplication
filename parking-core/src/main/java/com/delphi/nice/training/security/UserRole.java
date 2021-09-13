package com.delphi.nice.training.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(UserPermission.TICKET_READ, UserPermission.TICKET_READ_ALL,UserPermission.TICKET_WRITE));
    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
