package com.yifan.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysRoleDto implements Serializable {
    private static final long serialVersionUID = 3006083651120933663L;
    private Long id;
    private String code;
    private String name;
}
