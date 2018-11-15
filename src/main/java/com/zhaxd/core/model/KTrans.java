package com.zhaxd.core.model;

import java.util.Date;

public class KTrans {
    //转换ID
    private Integer transId;
    private Integer categoryId;
    //添加者
    private Integer addUser;
    //是否删除（1：存在；0：删除）
    private Integer delFlag;
    //编辑者
    private Integer editUser;
    //定时策略（外键ID）
    private Integer transQuartz;
    //转换执行记录（外键ID）
    private Integer transRecord;
    //转换的资源库ID
    private Integer transRepositoryId;
    //状态（1：正在运行；2：已停止）
    private Integer transStatus;
    //1:数据库资源库；2:上传的文件
    private Integer transType;
    //转换描述
    private String transDescription;
    //日志级别(basic，detail，error，debug，minimal，rowlevel）
    private String transLogLevel;
    //转换名称
    private String transName;
    //转换保存路径（可以是资源库中的路径也可以是服务器中保存作业文件的路径）
    private String transPath;
    //添加时间
    private Date addTime;
    //编辑时间
    private Date editTime;

    public KTrans() {
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
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

    public Integer getTransQuartz() {
        return transQuartz;
    }

    public void setTransQuartz(Integer transQuartz) {
        this.transQuartz = transQuartz;
    }

    public Integer getTransRecord() {
        return transRecord;
    }

    public void setTransRecord(Integer transRecord) {
        this.transRecord = transRecord;
    }

    public Integer getTransRepositoryId() {
        return transRepositoryId;
    }

    public void setTransRepositoryId(Integer transRepositoryId) {
        this.transRepositoryId = transRepositoryId;
    }

    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    public String getTransLogLevel() {
        return transLogLevel;
    }

    public void setTransLogLevel(String transLogLevel) {
        this.transLogLevel = transLogLevel;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTransPath() {
        return transPath;
    }

    public void setTransPath(String transPath) {
        this.transPath = transPath;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public KTrans(Integer transId, Integer categoryId, Integer addUser, Integer delFlag, Integer editUser, Integer transQuartz, Integer transRecord, Integer transRepositoryId, Integer transStatus, Integer transType, String transDescription, String transLogLevel, String transName, String transPath, Date addTime, Date editTime) {
        this.transId = transId;
        this.categoryId = categoryId;
        this.addUser = addUser;
        this.delFlag = delFlag;
        this.editUser = editUser;
        this.transQuartz = transQuartz;
        this.transRecord = transRecord;
        this.transRepositoryId = transRepositoryId;
        this.transStatus = transStatus;
        this.transType = transType;
        this.transDescription = transDescription;
        this.transLogLevel = transLogLevel;
        this.transName = transName;
        this.transPath = transPath;
        this.addTime = addTime;
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "KTrans{" +
                "transId=" + transId +
                ", categoryId=" + categoryId +
                ", addUser=" + addUser +
                ", delFlag=" + delFlag +
                ", editUser=" + editUser +
                ", transQuartz=" + transQuartz +
                ", transRecord=" + transRecord +
                ", transRepositoryId=" + transRepositoryId +
                ", transStatus=" + transStatus +
                ", transType=" + transType +
                ", transDescription='" + transDescription + '\'' +
                ", transLogLevel='" + transLogLevel + '\'' +
                ", transName='" + transName + '\'' +
                ", transPath='" + transPath + '\'' +
                ", addTime=" + addTime +
                ", editTime=" + editTime +
                '}';
    }
}