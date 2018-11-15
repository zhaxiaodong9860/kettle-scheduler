package com.zhaxd.common.kettle.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectory;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.RepositoryElementMetaInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.kettle.RepositoryTree;
import com.zhaxd.core.model.KRepository;

public class RepositoryUtil {

	public static Map<Integer, KettleDatabaseRepository> KettleDatabaseRepositoryCatch 
														= new HashMap<Integer, KettleDatabaseRepository>();
	
	
	public static void main(String[] args) throws KettleException{
	
		KRepository kRepository = new KRepository();
		kRepository.setRepositoryId(1);
		kRepository.setRepositoryName("repository");
		kRepository.setRepositoryUsername("admin");
		kRepository.setRepositoryPassword("admin");
		kRepository.setRepositoryType("MYSQL");
		kRepository.setDatabaseAccess("Native");
		kRepository.setDatabaseHost("localhost");
		kRepository.setDatabasePort("3306");
		kRepository.setDatabaseName("kettle");
		kRepository.setDatabaseUsername("root");
		kRepository.setDatabasePassword("123456");
		
		KettleEnvironment.init();
		
		KettleDatabaseRepository kettleDatabaseRepository = connectionRepository(kRepository);
		
		List<RepositoryTree> allRepositoryTreeList = new ArrayList<RepositoryTree>();
		
		List<RepositoryTree> repositoryTreeList = getAllDirectoryTreeList(kettleDatabaseRepository, "/", allRepositoryTreeList);

		for (RepositoryTree repositoryTree : repositoryTreeList){
			System.out.println(repositoryTree);
		}
	}
	
	
	public static String[] getDataBaseAccess(){
		String[] dataBaseAccess = DatabaseMeta.dbAccessTypeCode;
		
		return dataBaseAccess;
	}
	
	/**
	 * @Title connectionRepository
	 * @Description 连接资源库对象
	 * @param kRepository 资源库连接信息
	 * @throws KettleException
	 * @return void
	 */
	public static KettleDatabaseRepository connectionRepository(KRepository kRepository) throws KettleException{
		if (null != kRepository){
			//数据库连接元对象
	    	//DatabaseMeta databaseMeta = new DatabaseMeta("repository", "MYSQL", 
					//"Native", "localhost", "kettle-repository", "3306", "root", "1234");    	
	    	DatabaseMeta databaseMeta = new DatabaseMeta(null, kRepository.getRepositoryType(), 
	    			kRepository.getDatabaseAccess(), kRepository.getDatabaseHost(), kRepository.getDatabaseName(), 
	    			kRepository.getDatabasePort(), kRepository.getDatabaseUsername(), kRepository.getDatabasePassword());    	
	    	//资源库元对象
	        KettleDatabaseRepositoryMeta repositoryInfo = new KettleDatabaseRepositoryMeta();
	        repositoryInfo.setConnection(databaseMeta);
	        //资源库
	        KettleDatabaseRepository repository = new KettleDatabaseRepository();
	        repository.init(repositoryInfo);
	        repository.connect(kRepository.getRepositoryUsername(), kRepository.getRepositoryPassword());
	        //添加缓存
	        if (null != kRepository.getRepositoryId()){
	        	KettleDatabaseRepositoryCatch.put(kRepository.getRepositoryId(), repository);	
	        }	
	        return repository;	
		}
		return null;
	}	
	
	/**
	 * @Title disConnectionRepository
	 * @Description 断开资源库并删除缓存对象
	 * @param repository
	 * @param ID
	 * @return void
	 */
	public static void disConnectionRepository(KettleDatabaseRepository repository, Integer ID){
		repository.disconnect();
		repository.clearSharedObjectCache();
		KettleDatabaseRepositoryCatch.remove(ID);
	}
	
	/**
	 * @Title disConnectionAllRepository
	 * @Description 断开全部资源库
	 * @return void
	 */
	public static void disConnectionAllRepository(){
		KettleDatabaseRepositoryCatch.forEach((ID, repository) -> {
			repository.disconnect();
			repository.clearSharedObjectCache();			
		});
		KettleDatabaseRepositoryCatch.clear();
	}
	
	
	/**
	 * @Title getAllDirectoryTreeList
	 * @Description 递归调用获取全部的树形菜单
	 * @param kettleDatabaseRepository 资源库
	 * @param path 当前路径
	 * @param allRepositoryTreeList 所有的树形菜单
	 * @return
	 * @throws KettleException
	 * @return List<RepositoryTree> 所有的树形菜单
	 */
	public static List<RepositoryTree> getAllDirectoryTreeList(KettleDatabaseRepository kettleDatabaseRepository, 
			String path, List<RepositoryTree> allRepositoryTreeList) throws KettleException{
		//获取Job和Transformation和Directory的信息
		List<RepositoryTree> repositoryTreeList = getJobAndTrans(kettleDatabaseRepository, path);
		if (repositoryTreeList.size() != 0){
			for (RepositoryTree repositoryTree : repositoryTreeList){
				//如果有子Directory或者Job和Transformation。那么递归遍历
				if (!repositoryTree.isLasted()){
					getAllDirectoryTreeList(kettleDatabaseRepository, repositoryTree.getPath(), allRepositoryTreeList);
					allRepositoryTreeList.add(repositoryTree);
				}else{
					allRepositoryTreeList.add(repositoryTree);
				}
			}
		}				
		return allRepositoryTreeList;		
	}
	
	/**
	 * @Title getJobAndTrans
	 * @Description 获取Job和Transformation和Directory的信息
	 * @param repository
	 * @param path
	 * @return RepositoryTree的集合
	 * @throws KettleException
	 * @return List<RepositoryTree>
	 */
	public static List<RepositoryTree> getJobAndTrans(KettleDatabaseRepository repository, 
			String path) throws KettleException {
		//获取当前的路径信息
		RepositoryDirectoryInterface rDirectory = repository.loadRepositoryDirectoryTree().findDirectory(path);
		//获取Directory信息
		List<RepositoryTree> repositoryTreeList = getDirectory(repository, rDirectory);
		//获取Job和Transformation的信息
		List<RepositoryElementMetaInterface> li = repository.getJobAndTransformationObjects(rDirectory.getObjectId(), false);
		if (null != li) {
			for (RepositoryElementMetaInterface repel : li) {
				if (Constant.TYPE_JOB.equals(repel.getObjectType().toString())) {						
					RepositoryTree repositoryTree = new RepositoryTree();
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(Constant.TYPE_JOB).append(rDirectory.getObjectId().toString()).append("@").append(repel.getObjectId().toString());
					repositoryTree.setId(stringBuilder.toString());
					repositoryTree.setParent(rDirectory.getObjectId().toString());
					repositoryTree.setText(repel.getName());
					repositoryTree.setType(Constant.TYPE_JOB);
					repositoryTree.setLasted(true);
					repositoryTreeList.add(repositoryTree);
				}else if (Constant.TYPE_TRANS.equals(repel.getObjectType().toString())){
					RepositoryTree repositoryTree = new RepositoryTree();
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(Constant.TYPE_TRANS).append(rDirectory.getObjectId().toString()).append("@").append(repel.getObjectId().toString());
					repositoryTree.setId(stringBuilder.toString());
					repositoryTree.setParent(rDirectory.getObjectId().toString());
					repositoryTree.setText(repel.getName());
					repositoryTree.setType(Constant.TYPE_TRANS);
					repositoryTree.setLasted(true);
					repositoryTreeList.add(repositoryTree);
				}
			}
		}
		return repositoryTreeList;
	}
	
	/**
	 * @Title getDirectory
	 * @Description 获取Directory信息
	 * @param repository
	 * @param rDirectory
	 * @return
	 * @throws KettleException
	 * @return List<RepositoryTree>
	 */
	private static List<RepositoryTree> getDirectory(KettleDatabaseRepository repository, 
			RepositoryDirectoryInterface rDirectory) throws KettleException {
		List<RepositoryTree> repositoryTreeList = new ArrayList<RepositoryTree>();
		if (null != repository && null != rDirectory){			
			RepositoryDirectoryInterface tree = repository.loadRepositoryDirectoryTree().findDirectory(rDirectory.getObjectId());
			if (rDirectory.getNrSubdirectories() > 0){
				for (int i = 0; i < rDirectory.getNrSubdirectories(); i++) {				
					RepositoryDirectory subTree = tree.getSubdirectory(i);
					RepositoryTree repositoryTree = new RepositoryTree();
					repositoryTree.setId(subTree.getObjectId().toString());
					repositoryTree.setParent(rDirectory.getObjectId().toString());
					repositoryTree.setText(subTree.getName());
					repositoryTree.setPath(subTree.getPath());
					//判断是否还有子Directory或者Job和Transformation
					List<RepositoryElementMetaInterface> RepositoryElementMetaInterfaceList = 
							repository.getJobAndTransformationObjects(subTree.getObjectId(), false);					
					if (subTree.getNrSubdirectories() > 0 || RepositoryElementMetaInterfaceList.size() > 0){
						repositoryTree.setLasted(false);
					}else{
						repositoryTree.setLasted(true);
					}
					repositoryTreeList.add(repositoryTree);
				}	
			}					
		}
		return repositoryTreeList;	
	}
	
	
	
	
}
