package com.zhaxd.test.quartz;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zhaxd.web.quartz.QuartzManager;
import com.zhaxd.web.quartz.TransQuartz;

public class QuartzTest {

	@Test
	public void AddJobTest(){
		
		
		
		
		String cron = "*/5 * * * * ?";
		Map<String, Object> parameter = new HashMap<String, Object>();
		QuartzManager.addJob("work1", "/", "qqq1111", "aaa", TransQuartz.class, cron, parameter);
	}
	
	
	
	public static void main(String[] args){
		Map<String, Object> parameter = new HashMap<String, Object>();
		QuartzManager.addOnceJob("aaa", "aaaaaaaaaa", "qqq1111", "aaa", TransQuartz.class, parameter);
	}
}
