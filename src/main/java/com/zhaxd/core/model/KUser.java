package com.zhaxd.core.model;

import java.util.Date;

public class KUser {
	//用户ID
	private Integer uId ;
	//添加者
	private Integer addUser ;
	//是否删除（1：存在；0：删除）
	private Integer delFlag ;
	//编辑者
	private Integer editUser ;
	//用户账号
	private String uAccount ;
	//用户邮箱
	private String uEmail ;
	//用户昵称
	private String uNickname ;
	//用户密码
	private String uPassword ;
	//用于电话
	private String uPhone ;
	//添加时间
	private Date addTime ;
	//编辑时间
	private Date editTime ;
	
	public KUser() {
	}
	
	public Integer getuId(){
		return  uId;
	}
	public void setuId(Integer uId ){
		this.uId = uId;
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
	
	public String getuAccount(){
		return  uAccount;
	}
	public void setuAccount(String uAccount ){
		this.uAccount = uAccount;
	}
	
	public String getuEmail(){
		return  uEmail;
	}
	public void setuEmail(String uEmail ){
		this.uEmail = uEmail;
	}
	
	public String getuNickname(){
		return  uNickname;
	}
	public void setuNickname(String uNickname ){
		this.uNickname = uNickname;
	}
	
	public String getuPassword(){
		return  uPassword;
	}
	public void setuPassword(String uPassword ){
		this.uPassword = uPassword;
	}
	
	public String getuPhone(){
		return  uPhone;
	}
	public void setuPhone(String uPhone ){
		this.uPhone = uPhone;
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
		return "KUser [uId=" + uId + ", addUser=" + addUser + ", delFlag=" + delFlag + ", editUser=" + editUser
				+ ", uAccount=" + uAccount + ", uEmail=" + uEmail + ", uNickname=" + uNickname + ", uPassword="
				+ uPassword + ", uPhone=" + uPhone + ", addTime=" + addTime + ", editTime=" + editTime + "]";
	}
}