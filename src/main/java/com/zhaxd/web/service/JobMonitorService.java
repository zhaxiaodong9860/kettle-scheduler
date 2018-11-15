package com.zhaxd.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.mapper.KJobMonitorDao;
import com.zhaxd.core.model.KJobMonitor;
import com.zhaxd.web.utils.CommonUtils;

@Service
public class JobMonitorService {

    @Autowired
    private KJobMonitorDao kJobMonitorDao;

    /**
     * @param start 起始行数
     * @param size  每页数据条数
     * @param uId   用户ID
     * @return BootTablePage
     * @Title getList
     * @Description 获取作业监控分页列表
     */
    public BootTablePage getList(Integer start, Integer size, Integer monitorStatus, Integer categoryId, String jobName, Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(monitorStatus);
        if(StringUtils.isNotEmpty(jobName)){
            template.setJobName(jobName);
        }
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.pageQuery(template, start, size,categoryId);
        Long allCount = kJobMonitorDao.allCount(template,categoryId);
        BootTablePage bootTablePage = new BootTablePage();
        bootTablePage.setRows(kJobMonitorList);
        bootTablePage.setTotal(allCount);
        return bootTablePage;
    }

    /**
     * @param uId   用户ID
     * @return BootTablePage
     * @Title getList
     * @Description 获取作业监控不分页列表
     */
    public BootTablePage getList(Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.template(template);
        Collections.sort(kJobMonitorList);
        List<KJobMonitor> newKJobMonitorList = new ArrayList<KJobMonitor>();
        if (kJobMonitorList.size() >= 5) {
            newKJobMonitorList = kJobMonitorList.subList(0, 5);
        } else {
            newKJobMonitorList = kJobMonitorList;
        }
        BootTablePage bootTablePage = new BootTablePage();
        bootTablePage.setRows(newKJobMonitorList);
        bootTablePage.setTotal(5);
        return bootTablePage;
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllMonitorJob
     * @Description 获取所有的监控作业
     */
    public Integer getAllMonitorJob(Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.template(template);
        return kJobMonitorList.size();
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllSuccess
     * @Description 获取执行成功的数
     */
    public Integer getAllSuccess(Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.template(template);
        Integer allSuccess = 0;
        for (KJobMonitor KJobMonitor : kJobMonitorList) {
            allSuccess += KJobMonitor.getMonitorSuccess();
        }
        return allSuccess;
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllFail
     * @Description 获取执行失败的数
     */
    public Integer getAllFail(Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.template(template);
        Integer allSuccess = 0;
        for (KJobMonitor KJobMonitor : kJobMonitorList) {
            allSuccess += KJobMonitor.getMonitorFail();
        }
        return allSuccess;
    }

    /**
     * @param uId 用户ID
     * @return Map<String   ,   Object>
     * @Title getTransLine
     * @Description 获取7天内作业的折线图
     */
    public Map<String, Object> getJobLine(Integer uId) {
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(uId);
        List<KJobMonitor> kJobMonitorList = kJobMonitorDao.template(template);
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            resultList.add(i, 0);
        }
        if (kJobMonitorList != null && !kJobMonitorList.isEmpty()) {
            for (KJobMonitor KJobMonitor : kJobMonitorList) {
                String runStatus = KJobMonitor.getRunStatus();
                if (runStatus != null && runStatus.contains(",")) {
                    String[] startList = runStatus.split(",");
                    for (String startOnce : startList) {
                        String[] startAndStopTime = startOnce.split(Constant.RUNSTATUS_SEPARATE);
                        if (startAndStopTime.length != 2)
                            continue;
                        //得到一次任务的起始时间和结束时间的毫秒值
                        resultList = CommonUtils.getEveryDayData(Long.parseLong(startAndStopTime[0]), Long.parseLong(startAndStopTime[1]), resultList);
                    }
                }
            }
        }
        resultMap.put("name", "作业");
        resultMap.put("data", resultList);
        return resultMap;
    }
}