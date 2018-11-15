package com.zhaxd.core.model;

import java.util.Date;

public class KCategory {
    //分类ID
    private Integer categoryId ;
    //分类名称
    private String categoryName;
    //添加者
    private Integer addUser ;
    //是否删除（1：存在；0：删除）
    private Integer delFlag ;
    //编辑者
    private Integer editUser ;
    //添加时间
    private Date addTime ;
    //编辑时间
    private Date editTime ;

    public KCategory() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getAddUser() {
        return addUser;
    }

    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getEditUser() {
        return editUser;
    }

    public void setEditUser(Integer editUser) {
        this.editUser = editUser;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public KCategory(Integer categoryId, String categoryName, Integer addUser, Integer delFlag, Integer editUser, Date addTime, Date editTime) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.addUser = addUser;
        this.delFlag = delFlag;
        this.editUser = editUser;
        this.addTime = addTime;
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "KCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", addUser=" + addUser +
                ", delFlag=" + delFlag +
                ", editUser=" + editUser +
                ", addTime=" + addTime +
                ", editTime=" + editTime +
                '}';
    }
}
