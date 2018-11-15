package com.zhaxd.test.string;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.web.utils.DateTime;


public class StringTest {

	@Test
	public void test1(){
		String aaa = "/mysql-mysql";
		int lastIndexOf = StringUtils.lastIndexOf(aaa, "/");
		System.out.println(aaa.substring(0, lastIndexOf));
		System.out.println(aaa.substring(lastIndexOf + 1, aaa.length()));
	}
	
	
	@Test
	public void GenerateString(){		
		System.out.println(new Date().getTime());
		StringBuilder stringBuilder = new StringBuilder();
		DateTime dateTime = new DateTime();
		Integer addMinute = dateTime.second() >= 58 ? 2 : 1;
		stringBuilder.append("0").append(" ")
						.append(dateTime.minute() + addMinute).append(" ")
						.append(dateTime.hour(true)).append(" ")
						.append(dateTime.dayOfMonth()).append(" ")
						.append(dateTime.monthStartFromOne()).append(" ")
						.append("?").append(" ")
						.append(String.valueOf(dateTime.year()));
		System.out.println(stringBuilder.toString());
		System.out.println(new Date().getTime());
	}
	
	@Test
	public void testBR() throws IOException{
		String readFileToString = FileUtils.readFileToString(new File("F:\\tmp\\kettle-master\\1\\trans@kettle-test-6.2-log\\1496459302270.txt"), Constant.DEFAULT_ENCODING);
		System.out.println(readFileToString);
		
	}
	
}
