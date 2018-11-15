package com.zhaxd.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.mapper.KQuartzDao;
import com.zhaxd.core.model.KQuartz;

@Service
public class QuartzService {

	
	@Autowired
	private KQuartzDao kQuartzDao; 
	
	/**
	 * @Title getList
	 * @Description 获取定时策略列表
	 * @return 
	 * @throws KettleException
	 * @return List<KQuartz>
	 */
	public List<KQuartz> getList(Integer uId){
		List<KQuartz> resultList = new ArrayList<KQuartz>();
		KQuartz kQuartz = new KQuartz();
		kQuartz.setDelFlag(1);
		kQuartz.setAddUser(uId);
		resultList.addAll(kQuartzDao.template(kQuartz));		
		return resultList;
	}
	
	/**
	 * @Title getList
	 * @Description 获取分页列表
	 * @param start 起始行数
	 * @param size 每页行数
	 * @param uId 用户ID
	 * @return
	 * @throws KettleException
	 * @return BootTablePage
	 */
	public BootTablePage getList(Integer start, Integer size, Integer uId){
		KQuartz kQuartz = new KQuartz();
		kQuartz.setDelFlag(1);
		kQuartz.setAddUser(uId);
		List<KQuartz> kQuartzList = kQuartzDao.template(kQuartz, start, size);
		long allCount = kQuartzDao.templateCount(kQuartz);
		BootTablePage bootTablePage = new BootTablePage();
		bootTablePage.setRows(kQuartzList);
		bootTablePage.setTotal(allCount);
		return bootTablePage;
	}
	/**
	 * @Title getQuartz
	 * @Description 获取定时策略列表
	 * @param quartzId 定时策略ID
	 * @return
	 * @return KQuartz
	 */
	public KQuartz getQuartz(Integer quartzId){
		return kQuartzDao.single(quartzId);
	}

	/**
	 * @Title insert
	 * @Description 插入定时策略
	 * @param kQuartz 定时策略对象
	 * @param uId 用户ID
	 * @return void
	 */
	public void insert(KQuartz kQuartz, Integer uId){
		kQuartz.setAddTime(new Date());
		kQuartz.setAddUser(uId);
		kQuartz.setEditTime(new Date());
		kQuartz.setEditUser(uId);
		kQuartz.setDelFlag(1);
		kQuartzDao.insert(kQuartz);
	}
	/**
	 * @Title delete
	 * @Description 删除定时策略
	 * @param quartzId 定时策略ID
	 * @return void
	 */
	public void delete(Integer quartzId){
		KQuartz kQuartz = kQuartzDao.unique(quartzId);
		kQuartz.setDelFlag(0);
		kQuartzDao.updateById(kQuartz);
	}

	/**
	 * @Title update
	 * @Description 更新定时策略
	 * @param kQuartz 定时策略对象
	 * @param uId 用户ID
	 * @return void
	 */
	public void update(KQuartz kQuartz, Integer uId){
		kQuartz.setEditTime(new Date());
		kQuartz.setEditUser(uId);
		//只有不为null的字段才参与更新
		kQuartzDao.updateTemplateById(kQuartz);
	}
}