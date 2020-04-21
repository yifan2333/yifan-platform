package com.yifan.dto;

import com.yifan.entity.SysUserEntity;

import lombok.Data;

@Data
public class SysUserDto {

    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String phone;
    private Integer sex;
    private String type;

    public static SysUserDto getUser(SysUserEntity entity) {
        SysUserDto userDto = new SysUserDto();
        userDto.setUserId(entity.getUserId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setNickname(entity.getNickname());
        userDto.setHeadImgUrl(entity.getHeadImgUrl());
        userDto.setPhone(entity.getPhone());
        userDto.setSex(entity.getSex());
        userDto.setType(entity.getType());
        return userDto;
    }
}
