package com.delphi.nice.training.security;

public enum UserPermission {
    TICKET_READ("user:read"),
    TICKET_WRITE("user:write"),
    TICKET_READ_ALL("user:readAll");
    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
