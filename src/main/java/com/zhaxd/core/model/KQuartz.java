package com.zhaxd.core.model;

import java.util.Date;

public class KQuartz {
	//任务ID
	private Integer quartzId ;
	//添加者
	private Integer addUser ;
	//是否删除（1：存在；0：删除）
	private Integer delFlag ;
	//编辑者
	private Integer editUser ;
	//定时策略
	private String quartzCron ;
	//任务描述
	private String quartzDescription ;
	//添加时间
	private Date addTime ;
	//编辑时间
	private Date editTime ;
	
	public KQuartz() {
	}
	
	public Integer getQuartzId(){
		return  quartzId;
	}
	public void setQuartzId(Integer quartzId ){
		this.quartzId = quartzId;
	}
	
	public Integer getAddUser(){
		return  addUser;
	}
	public void setAddUser(Integer addUser ){
		this.addUser = addUser;
	}
	
	public Integer getDelFlag(){
		return  delFlag;
	}
	public void setDelFlag(Integer delFlag ){
		this.delFlag = delFlag;
	}
	
	public Integer getEditUser(){
		return  editUser;
	}
	public void setEditUser(Integer editUser ){
		this.editUser = editUser;
	}
	
	public String getQuartzCron(){
		return  quartzCron;
	}
	public void setQuartzCron(String quartzCron ){
		this.quartzCron = quartzCron;
	}
	
	public String getQuartzDescription(){
		return  quartzDescription;
	}
	public void setQuartzDescription(String quartzDescription ){
		this.quartzDescription = quartzDescription;
	}
	
	public Date getAddTime(){
		return  addTime;
	}
	public void setAddTime(Date addTime ){
		this.addTime = addTime;
	}
	
	public Date getEditTime(){
		return  editTime;
	}
	public void setEditTime(Date editTime ){
		this.editTime = editTime;
	}
	
}