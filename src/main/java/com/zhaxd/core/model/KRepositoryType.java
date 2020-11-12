package com.zhaxd.core.model;

import org.beetl.sql.core.annotatoin.SeqID;

public class KRepositoryType {
	@SeqID(name="REPOSITORY_TYPE_ID_SEQ")
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