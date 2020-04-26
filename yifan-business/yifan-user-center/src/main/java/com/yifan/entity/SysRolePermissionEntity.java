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
@Table(name = "sys_role_permission", schema = "user-center")
public class SysRolePermissionEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "role_id")
    private Long roleId;
    @Basic
    @Column(name = "permission_id")
    private Long permissionId;
    @Basic
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;
}
