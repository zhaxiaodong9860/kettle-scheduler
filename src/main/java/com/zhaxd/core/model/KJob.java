package com.zhaxd.core.model;

import java.util.Date;

public class KJob {
    //作业ID
    private Integer jobId;
    private Integer categoryId;
    //添加者
    private Integer addUser;
    //是否删除（1：存在；0：删除）
    private Integer delFlag;
    //编辑者
    private Integer editUser;
    //作业执行日志（外键ID）
    private Integer jobRecord;
    //定时策略（外键ID）
    private Integer jobQuartz;
    //作业的资源库ID
    private Integer jobRepositoryId;
    //状态（1：正在运行；2：已停止）
    private Integer jobStatus;
    //1:数据库资源库；2:上传的文件
    private Integer jobType;
    //任务描述
    private String jobDescription;
    //作业名称
    private String jobName;
    //作业保存路径（可以是资源库中的路径也可以是服务器中保存作业文件的路径）
    private String jobPath;
    //日志级别(basic，detail，error，debug，minimal，rowlevel）
    private String jobLogLevel;
    //添加时间
    private Date addTime;
    //编辑时间
    private Date editTime;

    public KJob() {
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public Integer getJobRecord() {
        return jobRecord;
    }

    public void setJobRecord(Integer jobRecord) {
        this.jobRecord = jobRecord;
    }

    public Integer getJobQuartz() {
        return jobQuartz;
    }

    public void setJobQuartz(Integer jobQuartz) {
        this.jobQuartz = jobQuartz;
    }

    public Integer getJobRepositoryId() {
        return jobRepositoryId;
    }

    public void setJobRepositoryId(Integer jobRepositoryId) {
        this.jobRepositoryId = jobRepositoryId;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobPath() {
        return jobPath;
    }

    public void setJobPath(String jobPath) {
        this.jobPath = jobPath;
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

    public String getJobLogLevel() {
        return jobLogLevel;
    }

    public void setJobLogLevel(String jobLogLevel) {
        this.jobLogLevel = jobLogLevel;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public KJob(Integer jobId, Integer categoryId, Integer addUser, Integer delFlag, Integer editUser, Integer jobRecord, Integer jobQuartz, Integer jobRepositoryId, Integer jobStatus, Integer jobType, String jobDescription, String jobName, String jobPath, String jobLogLevel, Date addTime, Date editTime) {
        this.jobId = jobId;
        this.categoryId = categoryId;
        this.addUser = addUser;
        this.delFlag = delFlag;
        this.editUser = editUser;
        this.jobRecord = jobRecord;
        this.jobQuartz = jobQuartz;
        this.jobRepositoryId = jobRepositoryId;
        this.jobStatus = jobStatus;
        this.jobType = jobType;
        this.jobDescription = jobDescription;
        this.jobName = jobName;
        this.jobPath = jobPath;
        this.jobLogLevel = jobLogLevel;
        this.addTime = addTime;
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "KJob{" +
                "jobId=" + jobId +
                ", categoryId=" + categoryId +
                ", addUser=" + addUser +
                ", delFlag=" + delFlag +
                ", editUser=" + editUser +
                ", jobRecord=" + jobRecord +
                ", jobQuartz=" + jobQuartz +
                ", jobRepositoryId=" + jobRepositoryId +
                ", jobStatus=" + jobStatus +
                ", jobType=" + jobType +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobPath='" + jobPath + '\'' +
                ", jobLogLevel='" + jobLogLevel + '\'' +
                ", addTime=" + addTime +
                ", editTime=" + editTime +
                '}';
    }
}