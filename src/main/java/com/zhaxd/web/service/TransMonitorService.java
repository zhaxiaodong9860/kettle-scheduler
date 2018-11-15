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
import com.zhaxd.core.mapper.KTransMonitorDao;
import com.zhaxd.core.model.KTransMonitor;
import com.zhaxd.web.utils.CommonUtils;

@Service
public class TransMonitorService {

    @Autowired
    private KTransMonitorDao kTransMonitorDao;

    /**
     * @param start 起始行数
     * @param size  每页数据条数
     * @param uId   用户ID
     * @return BootTablePage
     * @Title getList
     * @Description 获取分页列表
     */
    public BootTablePage getList(Integer start, Integer size, Integer monitorStatus, Integer categoryId, String transName, Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(monitorStatus);
        if (StringUtils.isNotEmpty(transName)) {
            template.setTransName(transName);
        }
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.pageQuery(template, start, size, categoryId);
        Long allCount = kTransMonitorDao.allCount(template, categoryId);
        BootTablePage bootTablePage = new BootTablePage();
        bootTablePage.setRows(kTransMonitorList);
        bootTablePage.setTotal(allCount);
        return bootTablePage;
    }

    /**
     * @param start 起始行数
     * @param size  每页数据条数
     * @param uId   用户ID
     * @return BootTablePage
     * @Title getList
     * @Description 获取不分页列表
     */
    public BootTablePage getList(Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.template(template);
        Collections.sort(kTransMonitorList);
        List<KTransMonitor> newKTransMonitorList = new ArrayList<KTransMonitor>();
        if (kTransMonitorList.size() >= 5) {
            newKTransMonitorList = kTransMonitorList.subList(0, 5);
        } else {
            newKTransMonitorList = kTransMonitorList;
        }
        BootTablePage bootTablePage = new BootTablePage();
        bootTablePage.setRows(newKTransMonitorList);
        bootTablePage.setTotal(5);
        return bootTablePage;
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllMonitorTrans
     * @Description 获取全部被监控的转换
     */
    public Integer getAllMonitorTrans(Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.template(template);
        return kTransMonitorList.size();
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllSuccess
     * @Description 获取所有转换执行成功的次数的和
     */
    public Integer getAllSuccess(Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.template(template);
        Integer allSuccess = 0;
        for (KTransMonitor KTransMonitor : kTransMonitorList) {
            allSuccess += KTransMonitor.getMonitorSuccess();
        }
        return allSuccess;
    }

    /**
     * @param uId 用户ID
     * @return Integer
     * @Title getAllFail
     * @Description 获取所有转换执行失败的次数的和
     */
    public Integer getAllFail(Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        template.setMonitorStatus(1);
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.template(template);
        Integer allSuccess = 0;
        for (KTransMonitor KTransMonitor : kTransMonitorList) {
            allSuccess += KTransMonitor.getMonitorFail();
        }
        return allSuccess;
    }

    /**
     * @param uId 用户ID
     * @return Map<String   ,   Object>
     * @Title getTransLine
     * @Description 获取7天内转换的折线图
     */
    public Map<String, Object> getTransLine(Integer uId) {
        KTransMonitor template = new KTransMonitor();
        template.setAddUser(uId);
        List<KTransMonitor> kTransMonitorList = kTransMonitorDao.template(template);
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        List<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            resultList.add(i, 0);
        }
        if (kTransMonitorList != null && !kTransMonitorList.isEmpty()) {
            for (KTransMonitor KTransMonitor : kTransMonitorList) {
                String runStatus = KTransMonitor.getRunStatus();
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
        resultMap.put("name", "转换");
        resultMap.put("data", resultList);
        return resultMap;
    }
}