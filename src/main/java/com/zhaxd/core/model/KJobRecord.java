package com.zhaxd.core.model;

import java.util.Date;

public class KJobRecord {
	//作业记录ID
	private Integer recordId ;
	//作业ID
	private Integer recordJob ;
	//任务执行结果（1：成功；2：失败）
	private Integer recordStatus ;
	//添加人
	private Integer addUser;
	//作业日志记录文件保存位置
	private String logFilePath ;
	//启动时间
	private Date startTime ;
	//停止时间
	private Date stopTime ;
	
	public KJobRecord() {
	}
	
	public Integer getRecordId(){
		return  recordId;
	}
	public void setRecordId(Integer recordId ){
		this.recordId = recordId;
	}
	
	public Integer getRecordJob(){
		return  recordJob;
	}
	public void setRecordJob(Integer recordJob ){
		this.recordJob = recordJob;
	}
	
	public Integer getRecordStatus(){
		return  recordStatus;
	}
	public void setRecordStatus(Integer recordStatus ){
		this.recordStatus = recordStatus;
	}
	
	public String getLogFilePath(){
		return  logFilePath;
	}
	public void setLogFilePath(String logFilePath ){
		this.logFilePath = logFilePath;
	}
	
	public Date getStartTime(){
		return  startTime;
	}
	public void setStartTime(Date startTime ){
		this.startTime = startTime;
	}
	
	public Date getStopTime(){
		return  stopTime;
	}
	public void setStopTime(Date stopTime ){
		this.stopTime = stopTime;
	}

	@Override
	public String toString() {
		return "KJobRecord [recordId=" + recordId + ", recordJob=" + recordJob + ", recordStatus=" + recordStatus
				+ ", addUser=" + addUser + ", logFilePath=" + logFilePath + ", startTime=" + startTime + ", stopTime="
				+ stopTime + "]";
	}

	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}
}