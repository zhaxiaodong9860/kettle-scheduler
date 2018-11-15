package com.zhaxd.web.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhaxd.core.mapper.KJobRecordDao;
import com.zhaxd.core.mapper.KTransRecordDao;
import com.zhaxd.core.model.KJobRecord;
import com.zhaxd.core.model.KTransRecord;

@Controller
@RequestMapping("/download/")
public class DownLoadRecordController {

	@Autowired
	private KJobRecordDao kJobRecordDao;
	
	@Autowired
	private KTransRecordDao kTransRecordDao;
	
	@RequestMapping("job/record.shtml")
	public ResponseEntity<byte[]> jobRecord(Integer recordId){
		KJobRecord kJobRecord = kJobRecordDao.unique(recordId);
		String logFilePath = kJobRecord.getLogFilePath();		
		HttpHeaders headers = new HttpHeaders();
		String fileName = logFilePath.substring(StringUtils.lastIndexOf(logFilePath, "/") + 1, logFilePath.length());		
		try {
			byte[] logFileBytes = FileUtils.readFileToByteArray(new File(logFilePath));
			String downFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			headers.setContentDispositionFormData("attachment", downFileName);     
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        return new ResponseEntity<byte[]>(logFileBytes, headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("trans/record.shtml")
	public ResponseEntity<byte[]> transRecord(Integer recordId){
		KTransRecord kTransRecord = kTransRecordDao.unique(recordId);
		String logFilePath = kTransRecord.getLogFilePath();		
		HttpHeaders headers = new HttpHeaders();
		String fileName = logFilePath.substring(StringUtils.lastIndexOf(logFilePath, "/") + 1, logFilePath.length());		
		try {
			byte[] logFileBytes = FileUtils.readFileToByteArray(new File(logFilePath));
			String downFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			headers.setContentDispositionFormData("attachment", downFileName);     
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        return new ResponseEntity<byte[]>(logFileBytes, headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
