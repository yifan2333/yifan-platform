package com.yifan.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sys_user", schema = "user-center")
@Data
public class SysUserEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "head_img_url")
    private String headImgUrl;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "sex")
    private Integer sex;
    @Basic
    @Column(name = "enabled")
    private Integer enabled;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
}
