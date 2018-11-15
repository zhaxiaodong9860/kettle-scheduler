package com.zhaxd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.dto.ResultDto;
import com.zhaxd.core.dto.kettle.RepositoryTree;
import com.zhaxd.core.dto.web.KRepositoryDto;
import com.zhaxd.core.model.KRepository;
import com.zhaxd.core.model.KUser;
import com.zhaxd.web.service.DataBaseRepositoryService;
import com.zhaxd.web.utils.DataValidate;
import com.zhaxd.web.utils.JsonUtils;

@RestController
@RequestMapping("/repository/")
public class RepositoryController {

	@Autowired
	private DataBaseRepositoryService dataBaseRepositoryService;
	
	@RequestMapping("database/getJobTree.shtml")
	public String getJobTree(Integer repositoryId){
		try {
			List<RepositoryTree> repositoryTreeList = dataBaseRepositoryService.getTreeList(repositoryId);
			List<RepositoryTree> newRepositoryTreeList = new ArrayList<RepositoryTree>();
			for(RepositoryTree repositoryTree : repositoryTreeList){
				if ("0".equals(repositoryTree.getParent())){
					repositoryTree.setParent("#");
				}
				if (repositoryTree.getId().indexOf("@") > 0){
					repositoryTree.setIcon("none");
				}
				if (Constant.TYPE_TRANS.equals(repositoryTree.getType())){
					Map<String,String> stateMap = new HashMap<String, String>();
					stateMap.put("disabled", "false");
					repositoryTree.setState(stateMap);
				}
				newRepositoryTreeList.add(repositoryTree);				
			}
			return JsonUtils.objectToJson(newRepositoryTreeList);
		} catch (KettleException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("database/getTransTree.shtml")
	public String getTransTree(Integer repositoryId){
		try {
			List<RepositoryTree> repositoryTreeList = dataBaseRepositoryService.getTreeList(repositoryId);
			List<RepositoryTree> newRepositoryTreeList = new ArrayList<RepositoryTree>();
			for(RepositoryTree repositoryTree : repositoryTreeList){
				if ("0".equals(repositoryTree.getParent())){
					repositoryTree.setParent("#");
				}
				if (repositoryTree.getId().indexOf("@") > 0){
					repositoryTree.setIcon("none");
				}
				if (Constant.TYPE_JOB.equals(repositoryTree.getType())){
					Map<String,String> stateMap = new HashMap<String, String>();
					stateMap.put("disabled", "false");
					repositoryTree.setState(stateMap);
				}
				newRepositoryTreeList.add(repositoryTree);				
			}
			return JsonUtils.objectToJson(newRepositoryTreeList);
		} catch (KettleException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	@RequestMapping("database/ckeck.shtml")
	public String ckeck(KRepositoryDto kRepositoryDto){	
		if (DataValidate.AllNotEmpty(kRepositoryDto)){
			try {
				KRepository kRepository = new KRepository();
				BeanUtils.copyProperties(kRepositoryDto, kRepository);
				if (dataBaseRepositoryService.ckeck(kRepository)){
					return ResultDto.success("success");	
				}else {
					return ResultDto.success("fail");	
				}
			} catch (KettleException e) {
				e.printStackTrace();
				return ResultDto.success("fail");
			}
		}else {
			return ResultDto.success("fail");
		}		
	}
	
	@RequestMapping("database/getSimpleList.shtml")
	public String getSimpleList(HttpServletRequest request){
		try {
			KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
			return JsonUtils.objectToJson(dataBaseRepositoryService.getList(kUser.getuId()));
		} catch (KettleException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("database/getList.shtml")
	public String getList(Integer offset, Integer limit, HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		BootTablePage list = null;
		list = dataBaseRepositoryService.getList(offset, limit, kUser.getuId());					
		return JsonUtils.objectToJson(list);		
	}
	
	@RequestMapping("database/getType.shtml")
	public String getType(){		
		return JsonUtils.objectToJson(dataBaseRepositoryService.getRepositoryTypeList());
	}
	
	@RequestMapping("database/getAccess.shtml")
	public String getAccess(){
		return JsonUtils.objectToJson(dataBaseRepositoryService.getAccess());
	}
	
	@RequestMapping("database/getKRepository.shtml")
	public String getKRepository(Integer repositoryId){
		return ResultDto.success(dataBaseRepositoryService.getKRepository(repositoryId));
	}
	
	@RequestMapping("database/insert.shtml")
	public String insert(KRepository kRepository, HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		dataBaseRepositoryService.insert(kRepository, kUser.getuId());
		return ResultDto.success();	
	}
		
	@RequestMapping("database/update.shtml")
	public String update(KRepository kRepository, HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		dataBaseRepositoryService.update(kRepository, kUser.getuId());
		return ResultDto.success();	
	}
	@RequestMapping("database/delete.shtml")
	public String delete(Integer repositoryId){
		dataBaseRepositoryService.delete(repositoryId);
		return ResultDto.success();
	}
}
