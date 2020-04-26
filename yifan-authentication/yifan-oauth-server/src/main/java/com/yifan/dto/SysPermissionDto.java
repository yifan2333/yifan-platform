package com.yifan.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysPermissionDto implements Serializable {
    private static final long serialVersionUID = -4834393523003310438L;
    private Long permissionId;
    private String permission;
    private String name;
}
