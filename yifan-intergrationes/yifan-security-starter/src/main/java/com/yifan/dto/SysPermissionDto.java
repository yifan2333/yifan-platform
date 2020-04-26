package com.yifan.dto;

import java.io.Serializable;

public class SysPermissionDto  implements Serializable {
    private static final long serialVersionUID = -4834393523003310438L;
    private long permissionId;
    private String permission;
    private String name;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
