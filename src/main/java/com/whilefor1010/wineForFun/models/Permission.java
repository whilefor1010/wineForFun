package com.whilefor1010.wineForFun.models;

public enum Permission {
    WINES_READ("wines:read"),
    WINES_WRITE("wines:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
