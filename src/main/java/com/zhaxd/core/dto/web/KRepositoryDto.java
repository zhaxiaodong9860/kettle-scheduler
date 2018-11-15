package com.zhaxd.core.dto.web;

public class KRepositoryDto {

	//资源库数据库访问模式（"Native", "ODBC", "OCI", "Plugin", "JNDI")
	private String databaseAccess;
	//资源库数据库主机名或者IP地址
	private String databaseHost;
	//资源库数据库名称
	private String databaseName;
	//数据库登录密码
	private String databasePassword;
	//资源库数据库端口号
	private String databasePort;
	//数据库登录账号
	private String databaseUsername;
	//资源库名称
	private String repositoryName;
	//登录密码
	private String repositoryPassword;
	//资源库数据库类型（MYSQL、ORACLE）
	private String repositoryType;
	//登录用户名
	private String repositoryUsername;
	
	public String getDatabaseAccess() {
		return databaseAccess;
	}
	public void setDatabaseAccess(String databaseAccess) {
		this.databaseAccess = databaseAccess;
	}
	public String getDatabaseHost() {
		return databaseHost;
	}
	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getDatabasePort() {
		return databasePort;
	}
	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public String getRepositoryPassword() {
		return repositoryPassword;
	}
	public void setRepositoryPassword(String repositoryPassword) {
		this.repositoryPassword = repositoryPassword;
	}
	public String getRepositoryType() {
		return repositoryType;
	}
	public void setRepositoryType(String repositoryType) {
		this.repositoryType = repositoryType;
	}
	public String getRepositoryUsername() {
		return repositoryUsername;
	}
	public void setRepositoryUsername(String repositoryUsername) {
		this.repositoryUsername = repositoryUsername;
	}
}