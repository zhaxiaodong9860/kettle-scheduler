package com.zhaxd.web.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.dto.ResultDto;
import com.zhaxd.core.model.KTrans;
import com.zhaxd.core.model.KUser;
import com.zhaxd.web.service.TransService;
import com.zhaxd.web.utils.JsonUtils;

@RestController
@RequestMapping("/trans/")
public class TransController {

    @Autowired
    private TransService transService;


    @RequestMapping("getSimpleList.shtml")
    public String getSimpleList(HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        return JsonUtils.objectToJson(transService.getList(kUser.getuId()));
    }

    @RequestMapping("getList.shtml")
    public String getList(Integer offset, Integer limit, Integer categoryId, String transName, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        BootTablePage list = transService.getList(offset, limit, categoryId, transName, kUser.getuId());
        return JsonUtils.objectToJson(list);
    }

    @RequestMapping("delete.shtml")
    public String delete(Integer transId) {
        transService.delete(transId);
        return ResultDto.success();
    }

    @RequestMapping("uploadTrans.shtml")
    public String uploadJob(MultipartFile transFile, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        try {
            String saveFilePath = transService.saveFile(kUser.getuId(), transFile);
            return ResultDto.success(saveFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("insert.shtml")
    public String insert(KTrans kTrans, String customerQuarz, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        Integer uId = kUser.getuId();
        if (transService.check(kTrans.getTransRepositoryId(), kTrans.getTransPath(), uId)) {
            try {
                transService.insert(kTrans, uId, customerQuarz);
                return ResultDto.success();
            } catch (SQLException e) {
                e.printStackTrace();
                return ResultDto.fail("添加作业失败");
            }
        } else {
            return ResultDto.fail("该作业已经添加过了");
        }
    }

    @RequestMapping("getTrans.shtml")
    public String getTrans(Integer transId) {
        return ResultDto.success(transService.getTrans(transId));
    }

    @RequestMapping("update.shtml")
    public String update(KTrans kTrans, String customerQuarz, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        try {
            transService.update(kTrans, customerQuarz, kUser.getuId());
            return ResultDto.success();
        } catch (Exception e) {
            return ResultDto.success(e.toString());
        }
    }

    @RequestMapping("start.shtml")
    public String start(Integer transId) {
        transService.start(transId);
        return ResultDto.success();
    }

    @RequestMapping("stop.shtml")
    public String stop(Integer transId) {
        transService.stop(transId);
        return ResultDto.success();
    }

    @RequestMapping("startAll.shtml")
    public String startAll(Integer categoryId, String transName, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        transService.startAll(categoryId, transName, kUser.getuId());
        return ResultDto.success();
    }

    @RequestMapping("stopAll.shtml")
    public String stopAll(Integer categoryId, String transName, HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        transService.stopAll(categoryId, transName, kUser.getuId());
        return ResultDto.success();
    }

    @RequestMapping("getStartTaskCount.shtml")
    public String getStartTaskCount(Integer categoryId, String transName,HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        return JsonUtils.objectToJson(transService.getStartTaskCount(categoryId, transName, kUser.getuId()));
    }

    @RequestMapping("getStopTaskCount.shtml")
    public String getStopTaskCount(Integer categoryId, String transName,HttpServletRequest request) {
        KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
        return JsonUtils.objectToJson(transService.getStopTaskCount(categoryId, transName, kUser.getuId()));
    }
    @RequestMapping({"getTransRunState.shtml"})
    public String getTransRunState(Integer transId) {
        return JsonUtils.objectToJson(this.transService.getTransRunState(transId));
    }
}
