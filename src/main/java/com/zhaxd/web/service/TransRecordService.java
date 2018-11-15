package com.zhaxd.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.mapper.KTransRecordDao;
import com.zhaxd.core.model.KTransRecord;

@Service
public class TransRecordService {

	@Autowired
	private KTransRecordDao kTransRecordDao;
	
	/**
	 * @Title getList
	 * @Description 获取列表
	 * @param start 其实行数
	 * @param size 结束行数
	 * @param uId 用户ID
	 * @param transId 转换ID
	 * @return
	 * @return BootTablePage
	 */
	public BootTablePage getList(Integer start, Integer size, Integer uId, Integer transId){
		KTransRecord template = new KTransRecord();
		template.setAddUser(uId);
		if (transId != null){
			template.setRecordTrans(transId);
		}
		List<KTransRecord> kTransRecordList = kTransRecordDao.template(template, start, size);
		long totalCount = kTransRecordDao.templateCount(template);
		BootTablePage bootTablePage = new BootTablePage();
		bootTablePage.setRows(kTransRecordList);
		bootTablePage.setTotal(totalCount);
		return bootTablePage;
	}
	
	/**
	 * @Title getLogContent
	 * @Description 转换日志内容
	 * @param recordId 转换记录ID
	 * @return
	 * @throws IOException
	 * @return String
	 */
	public String getLogContent(Integer recordId) throws IOException{
		KTransRecord kTransRecord = kTransRecordDao.unique(recordId);
		String logFilePath = kTransRecord.getLogFilePath();
		return FileUtils.readFileToString(new File(logFilePath), Constant.DEFAULT_ENCODING);
	}
	
}