package com.zhaxd.core.model;

import java.util.Date;

public class KJobMonitor implements Comparable<KJobMonitor>{
	//监控作业ID
	private Integer monitorId ;
	//添加人
	private Integer addUser ;
	//失败次数
	private Integer monitorFail ;
	//监控的作业ID
	private Integer monitorJob ;
	//监控状态（是否启动，1:启动；2:停止）
	private Integer monitorStatus ;
	//成功次数
	private Integer monitorSuccess ;
	//运行状态（起始时间-结束时间,起始时间-结束时间……）
	private String runStatus ;
	//上次执行时间
	private Date lastExecuteTime;
	//下次执行时间
	private Date nextExecuteTime;

	private String jobName;

	private String categoryName;

	public KJobMonitor() {
	}
	
	public Integer getMonitorId(){
		return  monitorId;
	}
	public void setMonitorId(Integer monitorId){
		this.monitorId = monitorId;
	}
	public Integer getAddUser(){
		return  addUser;
	}
	public void setAddUser(Integer addUser){
		this.addUser = addUser;
	}
	public Integer getMonitorFail(){
		return  monitorFail;
	}
	public void setMonitorFail(Integer monitorFail){
		this.monitorFail = monitorFail;
	}
	public Integer getMonitorJob(){
		return  monitorJob;
	}
	public void setMonitorJob(Integer monitorJob){
		this.monitorJob = monitorJob;
	}
	public Integer getMonitorStatus(){
		return  monitorStatus;
	}
	public void setMonitorStatus(Integer monitorStatus){
		this.monitorStatus = monitorStatus;
	}
	public Integer getMonitorSuccess(){
		return  monitorSuccess;
	}
	public void setMonitorSuccess(Integer monitorSuccess){
		this.monitorSuccess = monitorSuccess;
	}
	public String getRunStatus(){
		return  runStatus;
	}
	public void setRunStatus(String runStatus){
		this.runStatus = runStatus;
	}

	public Date getLastExecuteTime() {
		return lastExecuteTime;
	}

	public void setLastExecuteTime(Date lastExecuteTime) {
		this.lastExecuteTime = lastExecuteTime;
	}

	public Date getNextExecuteTime() {
		return nextExecuteTime;
	}

	public void setNextExecuteTime(Date nextExecuteTime) {
		this.nextExecuteTime = nextExecuteTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
    public int compareTo(KJobMonitor kJobMonitor) {    
        return this.getMonitorSuccess() - kJobMonitor.getMonitorSuccess();//按照成功次数排序;  
    }

	public KJobMonitor(Integer monitorId, Integer addUser, Integer monitorFail, Integer monitorJob, Integer monitorStatus, Integer monitorSuccess, String runStatus, Date lastExecuteTime, Date nextExecuteTime, String jobName, String categoryName) {
		this.monitorId = monitorId;
		this.addUser = addUser;
		this.monitorFail = monitorFail;
		this.monitorJob = monitorJob;
		this.monitorStatus = monitorStatus;
		this.monitorSuccess = monitorSuccess;
		this.runStatus = runStatus;
		this.lastExecuteTime = lastExecuteTime;
		this.nextExecuteTime = nextExecuteTime;
		this.jobName = jobName;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "KJobMonitor{" +
				"monitorId=" + monitorId +
				", addUser=" + addUser +
				", monitorFail=" + monitorFail +
				", monitorJob=" + monitorJob +
				", monitorStatus=" + monitorStatus +
				", monitorSuccess=" + monitorSuccess +
				", runStatus='" + runStatus + '\'' +
				", lastExecuteTime=" + lastExecuteTime +
				", nextExecuteTime=" + nextExecuteTime +
				", jobName='" + jobName + '\'' +
				", categoryName='" + categoryName + '\'' +
				'}';
	}
}