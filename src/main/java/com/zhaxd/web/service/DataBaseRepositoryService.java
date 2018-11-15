package com.zhaxd.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaxd.common.kettle.repository.RepositoryUtil;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.dto.kettle.RepositoryTree;
import com.zhaxd.core.mapper.KRepositoryDao;
import com.zhaxd.core.mapper.KRepositoryTypeDao;
import com.zhaxd.core.model.KRepository;
import com.zhaxd.core.model.KRepositoryType;

@Service
public class DataBaseRepositoryService {

	
	@Autowired
	private KRepositoryDao kRepositoryDao;
	
	@Autowired
	private KRepositoryTypeDao kRepositoryTypeDao; 
	
	/**
	 * @Title getRepositoryTreeList
	 * @Description 获取数据库资源库的树形菜单
	 * @param repositoryId
	 * @return
	 * @throws KettleException
	 * @return List<RepositoryTree>
	 */
	public List<RepositoryTree> getTreeList(Integer repositoryId) throws KettleException{
		KettleDatabaseRepository kettleDatabaseRepository = null;
		List<RepositoryTree> allRepositoryTreeList = new ArrayList<RepositoryTree>();
		if (RepositoryUtil.KettleDatabaseRepositoryCatch.containsKey(repositoryId)){
			kettleDatabaseRepository = RepositoryUtil.KettleDatabaseRepositoryCatch.get(repositoryId);
		}else {
			KRepository kRepository = kRepositoryDao.unique(repositoryId);
			kettleDatabaseRepository = RepositoryUtil.connectionRepository(kRepository);
		}
		if (null != kettleDatabaseRepository){
			List<RepositoryTree> repositoryTreeList = new ArrayList<RepositoryTree>();
			allRepositoryTreeList = RepositoryUtil.getAllDirectoryTreeList(kettleDatabaseRepository, "/", repositoryTreeList);	
		}
		return allRepositoryTreeList;
	}
	
	/**
	 * @Title ckeck
	 * @Description 判断是否可以连接上资源库
	 * @param kRepository
	 * @return
	 * @throws KettleException
	 * @return boolean
	 */
	public boolean ckeck(KRepository kRepository) throws KettleException{
		KettleDatabaseRepository kettleDatabaseRepository = RepositoryUtil.connectionRepository(kRepository);
		if (kettleDatabaseRepository != null){
			if (kettleDatabaseRepository.isConnected()){
				return true;
			}else{
				return false;	
			}
		}else{
			return false;
		}
	}
	
	/**
	 * @Title getList
	 * @Description 获取列表，不分页
	 * @param uId 用户ID
	 * @return
	 * @throws KettleException
	 * @return List<KRepository>
	 */
	public List<KRepository> getList(Integer uId) throws KettleException{
		KRepository kRepository = new KRepository();
		kRepository.setAddUser(uId);
		kRepository.setDelFlag(1);		
		return kRepositoryDao.template(kRepository);
	}
	
	/**
	 * @Title getList
	 * @Description 获取列表带分页
	 * @param start 其实行数
	 * @param size 每页数据条数
	 * @param uId 用户ID
	 * @return
	 * @return BootTablePage
	 */
	public BootTablePage getList(Integer start, Integer size, Integer uId){
		KRepository kRepository = new KRepository();
		kRepository.setAddUser(uId);
		kRepository.setDelFlag(1);	
		List<KRepository> kRepositoryList = kRepositoryDao.template(kRepository);
		long allCount = kRepositoryDao.templateCount(kRepository);
		BootTablePage bootTablePage = new BootTablePage();
		bootTablePage.setRows(kRepositoryList);
		bootTablePage.setTotal(allCount);
		return bootTablePage;
	}
	
	/**
	 * @Title getRepositoryTypeList
	 * @Description 获取资源库类别列表
	 * @return 
	 * @return List<KRepositoryType>
	 */
	public List<KRepositoryType> getRepositoryTypeList(){
		return kRepositoryTypeDao.all();
	}
	
	/**
	 * @Title getKRepository
	 * @Description 获取资源库对象
	 * @param repositoryId 资源库ID
	 * @return
	 * @return KRepository
	 */
	public KRepository getKRepository(Integer repositoryId){
		//如果根据主键没有获取到对象，返回null
		return kRepositoryDao.single(repositoryId);
	}
	
	/**
	 * @Title getAccess
	 * @Description 获取资源库访问类型
	 * @return 
	 * @return String[]
	 */
	public String[] getAccess(){
		return RepositoryUtil.getDataBaseAccess();
	}
	
	/**
	 * @Title insert
	 * @Description 插入资源库
	 * @param kRepository 资源库对象
	 * @param uId 用户ID
	 * @return void
	 */
	public void insert(KRepository kRepository, Integer uId){
		kRepository.setAddTime(new Date());
		kRepository.setAddUser(uId);
		kRepository.setEditTime(new Date());
		kRepository.setEditUser(uId);
		kRepository.setDelFlag(1);
		kRepositoryDao.insert(kRepository);
	}
	
	/**
	 * @Title update
	 * @Description 更新资源库
	 * @param kRepository 资源库对象
	 * @param uId 用户ID
	 * @return void
	 */
	public void update(KRepository kRepository, Integer uId){
		kRepository.setEditTime(new Date());
		kRepository.setEditUser(uId);
		//只有不为null的字段才参与更新
		kRepositoryDao.updateTemplateById(kRepository);
	}
	/**
	 * @Title delete
	 * @Description 删除资源库
	 * @param repositoryId 资源库ID
	 * @return void
	 */
	public void delete(Integer repositoryId){
		KRepository kRepository = kRepositoryDao.unique(repositoryId);
		kRepository.setDelFlag(0);
		kRepositoryDao.updateById(kRepository);
	}
}
