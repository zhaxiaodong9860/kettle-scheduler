package com.zhaxd.web.quartz.model;

public class DBConnectionModel {

	private String connectionDriveClassName;
	private String connectionUrl;
	private String connectionUser;
	private String connectionPassword;
	public String getConnectionDriveClassName() {
		return connectionDriveClassName;
	}
	public void setConnectionDriveClassName(String connectionDriveClassName) {
		this.connectionDriveClassName = connectionDriveClassName;
	}
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}
	public String getConnectionUser() {
		return connectionUser;
	}
	public void setConnectionUser(String connectionUser) {
		this.connectionUser = connectionUser;
	}
	public String getConnectionPassword() {
		return connectionPassword;
	}
	public void setConnectionPassword(String connectionPassword) {
		this.connectionPassword = connectionPassword;
	}
	public DBConnectionModel(String connectionDriveClassName, String connectionUrl, String connectionUser,
			String connectionPassword) {
		this.connectionDriveClassName = connectionDriveClassName;
		this.connectionUrl = connectionUrl;
		this.connectionUser = connectionUser;
		this.connectionPassword = connectionPassword;
	}
}
