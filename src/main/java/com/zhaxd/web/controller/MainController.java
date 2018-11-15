package com.zhaxd.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.dto.ResultDto;
import com.zhaxd.core.model.KUser;
import com.zhaxd.web.service.JobMonitorService;
import com.zhaxd.web.service.TransMonitorService;
import com.zhaxd.web.utils.JsonUtils;

@RestController
@RequestMapping("/main/")
public class MainController {

	@Autowired
	private TransMonitorService transMonitorService;
	
	@Autowired
	private JobMonitorService jobMonitorService;
	
	/**
	 * @Title allRuning
	 * @Description 
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("allRuning.shtml")
	public String allRuning(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		Integer allMonitorTrans = transMonitorService.getAllMonitorTrans(kUser.getuId());
		Integer allMonitorJob = jobMonitorService.getAllMonitorJob(kUser.getuId());
		Integer allRuning = allMonitorTrans + allMonitorJob; 
		return JsonUtils.objectToJson(allRuning);
	}
	
	/**
	 * @Title getTransList
	 * @Description 获取转换的Top5
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("getTransList.shtml")
	public String getTransList(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		BootTablePage list = transMonitorService.getList(kUser.getuId());
		return JsonUtils.objectToJson(list);
	}
	
	/**
	 * @Title getJobList
	 * @Description 获取作业的Top5
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("getJobList.shtml")
	public String getJobList(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		BootTablePage list = jobMonitorService.getList(kUser.getuId());
		return JsonUtils.objectToJson(list);
	}
	
	/**
	 * 
	 * @Title getKettleLine
	 * @Description TODO
	 * @return
	 * @return String
	 * @throws ParseException 
	 */
	@RequestMapping("getKettleLine.shtml")
	public String getKettleLine(HttpServletRequest request){		
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<String> dateList = new ArrayList<String>();
		for (int i = -6; i <= 0; i++){
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.DATE, i);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormat = simpleDateFormat.format(instance.getTime());
			dateList.add(dateFormat);
		}
		resultMap.put("legend", dateList);
		Map<String, Object> transLine = transMonitorService.getTransLine(kUser.getuId());
		resultMap.put("trans", transLine);
		Map<String, Object> jobLine = jobMonitorService.getJobLine(kUser.getuId());
		resultMap.put("job", jobLine);
		return ResultDto.success(resultMap);
	}
}
