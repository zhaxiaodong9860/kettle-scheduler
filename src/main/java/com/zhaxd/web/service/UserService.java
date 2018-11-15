package com.zhaxd.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaxd.common.toolkit.MD5Utils;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.mapper.KUserDao;
import com.zhaxd.core.model.KUser;

@Service
public class UserService {

	@Autowired
	private KUserDao kUserDao;
		
	/**
	 * @Title login
	 * @Description 登陆
	 * @param kUser 用户信息对象
	 * @return
	 * @return KUser
	 */
	public KUser login(KUser kUser){		
		KUser template = new KUser();
		template.setDelFlag(1);
		template.setuAccount(kUser.getuAccount());
		KUser user = kUserDao.templateOne(template);
		if (null != user){
			if (user.getuPassword().equals(MD5Utils.Encrypt(kUser.getuPassword(), true))){
				return user;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * @Title isAdmin
	 * @Description 用户是否为管理员
	 * @param uId 用户ID
	 * @return
	 * @return boolean
	 */
	public boolean isAdmin(Integer uId){
		KUser kUser = kUserDao.unique(uId);
		if ("admin".equals(kUser.getuAccount())){
			return true;
		}else {
			return false;	
		}
	}
	
	/**
	 * @Title getList
	 * @Description 获取用户分页列表
	 * @param start 其实行数
	 * @param size 每页显示行数
	 * @return 
	 * @return BootTablePage
	 */
	public BootTablePage getList(Integer start, Integer size){
		KUser template = new KUser();
		template.setDelFlag(1);
		List<KUser> kUserList = kUserDao.template(template, start, size);
		long allCount = kUserDao.templateCount(template);
		BootTablePage bootTablePage = new BootTablePage();
		bootTablePage.setRows(kUserList);
		bootTablePage.setTotal(allCount);
		return bootTablePage;
	}
	
	/**
	 * @Title delete
	 * @Description 删除用户
	 * @param uId 用户ID
	 * @return void
	 */
	public void delete(Integer uId){
		KUser kUser = kUserDao.unique(uId);
		kUser.setDelFlag(0);
		kUserDao.updateById(kUser);
	}
	
	/**
	 * @Title insert
	 * @Description 插入一个用户
	 * @param kUser
	 * @return void
	 */
	public void insert(KUser kUser, Integer uId){
		kUser.setuPassword(MD5Utils.Encrypt(kUser.getuPassword(), true));
		kUser.setAddTime(new Date());
		kUser.setAddUser(uId);
		kUser.setEditTime(new Date());
		kUser.setEditUser(uId);
		kUser.setDelFlag(1);
		kUserDao.insert(kUser);
	}
	/**
	 * @Title IsAccountExist
	 * @Description 判断账号是否存在
	 * @param uAccount
	 * @return void
	 */
	public boolean IsAccountExist(String uAccount) {
		KUser template = new KUser();
		template.setDelFlag(1);
		template.setuAccount(uAccount);
		KUser user = kUserDao.templateOne(template);
		if (null == user) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @Title getUser
	 * @Description 获取 用户
	 * @param uId 用户ID
	 * @return
	 * @return KUser
	 */
	public KUser getUser(Integer uId){
		return kUserDao.single(uId);
	}
	/**
	 * @Title update
	 * @Description 更新用户
	 * @param kUser 用户对象
	 * @param uId 用户ID
	 * @return void
	 */
	public void update(KUser kUser, Integer uId){
		kUser.setEditTime(new Date());
		kUser.setEditUser(uId);
		//只有不为null的字段才参与更新
		kUserDao.updateTemplateById(kUser);
	}
}