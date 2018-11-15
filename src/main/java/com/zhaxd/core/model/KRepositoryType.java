package com.zhaxd.core.model;

public class KRepositoryType {
	private Integer repositoryTypeId ;
	private String repositoryTypeCode ;
	private String repositoryTypeDes ;
	
	public KRepositoryType() {
	}
	
	public Integer getRepositoryTypeId(){
		return  repositoryTypeId;
	}
	public void setRepositoryTypeId(Integer repositoryTypeId ){
		this.repositoryTypeId = repositoryTypeId;
	}
	
	public String getRepositoryTypeCode(){
		return  repositoryTypeCode;
	}
	public void setRepositoryTypeCode(String repositoryTypeCode ){
		this.repositoryTypeCode = repositoryTypeCode;
	}
	
	public String getRepositoryTypeDes(){
		return  repositoryTypeDes;
	}
	public void setRepositoryTypeDes(String repositoryTypeDes ){
		this.repositoryTypeDes = repositoryTypeDes;
	}
	
}