package com.yifan.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu", schema = "user-center")
public class SysMenuEntity {
    private long id;
    private long parentId;
    private String name;
    private String url;
    private String path;
    private String css;
    private int sort;
    private Byte isMenu;
    private Byte hidden;
    private Timestamp updateTime;
    private Timestamp createTime;
    private Byte isDeleted;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id")
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "css")
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    @Basic
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "is_menu")
    public Byte getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Byte isMenu) {
        this.isMenu = isMenu;
    }

    @Basic
    @Column(name = "hidden")
    public Byte getHidden() {
        return hidden;
    }

    public void setHidden(Byte hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "is_deleted")
    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenuEntity that = (SysMenuEntity) o;
        return id == that.id &&
                parentId == that.parentId &&
                sort == that.sort &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(path, that.path) &&
                Objects.equals(css, that.css) &&
                Objects.equals(isMenu, that.isMenu) &&
                Objects.equals(hidden, that.hidden) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, url, path, css, sort, isMenu, hidden, updateTime, createTime, isDeleted);
    }
}
