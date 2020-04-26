package com.yifan.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sys_menu", schema = "user-center")
public class SysMenuEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "parent_id")
    private Long parentId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "path")
    private String path;
    @Basic
    @Column(name = "css")
    private String css;
    @Basic
    @Column(name = "sort")
    private int sort;
    @Basic
    @Column(name = "is_menu")
    private Byte isMenu;
    @Basic
    @Column(name = "hidden")
    private Byte hidden;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "is_deleted")
    private Integer isDeleted;
}
