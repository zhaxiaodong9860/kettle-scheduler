package com.zhaxd.core.model;

import java.util.Date;

public class KRepository {
	//ID
	private Integer repositoryId ;
	//添加者
	private Integer addUser ;
	//是否删除（1：存在；0：删除）
	private Integer delFlag ;
	//编辑者
	private Integer editUser ;
	//资源库数据库访问模式（"Native", "ODBC", "OCI", "Plugin", "JNDI")
	private String databaseAccess ;
	//资源库数据库主机名或者IP地址
	private String databaseHost ;
	//资源库数据库名称
	private String databaseName ;
	//数据库登录密码
	private String databasePassword ;
	//资源库数据库端口号
	private String databasePort ;
	//数据库登录账号
	private String databaseUsername ;
	//资源库名称
	private String repositoryName ;
	//登录密码
	private String repositoryPassword ;
	//资源库数据库类型（MYSQL、ORACLE）
	private String repositoryType ;
	//登录用户名
	private String repositoryUsername ;
	//添加时间
	private Date addTime ;
	//编辑时间
	private Date editTime ;
	
	public KRepository() {
	}
	
	public Integer getRepositoryId(){
		return  repositoryId;
	}
	public void setRepositoryId(Integer repositoryId ){
		this.repositoryId = repositoryId;
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
	
	public String getDatabaseAccess(){
		return  databaseAccess;
	}
	public void setDatabaseAccess(String databaseAccess ){
		this.databaseAccess = databaseAccess;
	}
	
	public String getDatabaseHost(){
		return  databaseHost;
	}
	public void setDatabaseHost(String databaseHost ){
		this.databaseHost = databaseHost;
	}
	
	public String getDatabaseName(){
		return  databaseName;
	}
	public void setDatabaseName(String databaseName ){
		this.databaseName = databaseName;
	}
	
	public String getDatabasePassword(){
		return  databasePassword;
	}
	public void setDatabasePassword(String databasePassword ){
		this.databasePassword = databasePassword;
	}
	
	public String getDatabasePort(){
		return  databasePort;
	}
	public void setDatabasePort(String databasePort ){
		this.databasePort = databasePort;
	}
	
	public String getDatabaseUsername(){
		return  databaseUsername;
	}
	public void setDatabaseUsername(String databaseUsername ){
		this.databaseUsername = databaseUsername;
	}
	
	public String getRepositoryName(){
		return  repositoryName;
	}
	public void setRepositoryName(String repositoryName ){
		this.repositoryName = repositoryName;
	}
	
	public String getRepositoryPassword(){
		return  repositoryPassword;
	}
	public void setRepositoryPassword(String repositoryPassword ){
		this.repositoryPassword = repositoryPassword;
	}
	
	public String getRepositoryType(){
		return  repositoryType;
	}
	public void setRepositoryType(String repositoryType ){
		this.repositoryType = repositoryType;
	}
	
	public String getRepositoryUsername(){
		return  repositoryUsername;
	}
	public void setRepositoryUsername(String repositoryUsername ){
		this.repositoryUsername = repositoryUsername;
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

	@Override
	public String toString() {
		return "KRepository [repositoryId=" + repositoryId + ", addUser=" + addUser + ", delFlag=" + delFlag
				+ ", editUser=" + editUser + ", databaseAccess=" + databaseAccess + ", databaseHost=" + databaseHost
				+ ", databaseName=" + databaseName + ", databasePassword=" + databasePassword + ", databasePort="
				+ databasePort + ", databaseUsername=" + databaseUsername + ", repositoryName=" + repositoryName
				+ ", repositoryPassword=" + repositoryPassword + ", repositoryType=" + repositoryType
				+ ", repositoryUsername=" + repositoryUsername + ", addTime=" + addTime + ", editTime=" + editTime
				+ "]";
	}
}