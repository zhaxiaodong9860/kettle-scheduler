package com.zhaxd.web.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtils {

    /**
     * @param uId     用户ID
     * @param jobFile 上传的作业文件
     * @return String
     * @throws IOException
     * @Title saveFile
     * @Description 保存上传的作业文件
     */
    public static String saveFile(Integer uId, String kettleFileRepository, MultipartFile jobFile) throws IOException {
        StringBuilder allLogFilePath = new StringBuilder();
        allLogFilePath.append(kettleFileRepository).append("/")
                .append(uId).append("/")
                .append(new Date().getTime()).append("/")
                .append(jobFile.getOriginalFilename());
        FileUtils.writeByteArrayToFile(new File(allLogFilePath.toString()), jobFile.getBytes());
        return allLogFilePath.toString();
    }

    /**
     * @param startTime  起始时间
     * @param stopTime   结束时间
     * @param resultList 递归的返回值
     * @return List<Integer>
     * @Title getEveryDayData
     * @Description 确定每一次运行属于哪一天
     */
    public static List<Integer> getEveryDayData(Long startTime, Long stopTime, List<Integer> resultList) {
        List<Long> timeList = new ArrayList<Long>();
        try {
            for (int i = -6; i <= 1; i++) {
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.DATE, i);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormat = simpleDateFormat.format(instance.getTime());
                Long time = simpleDateFormat.parse(dateFormat).getTime();
                timeList.add(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer startDay = 0;
        Integer stopDay = 0;
        for (int i = 0; i <= 6; i++) {
            if (timeList.get(i) < startTime && startTime < timeList.get(i + 1)) {
                startDay = i;
            }
            if (timeList.get(i) < stopTime && stopTime < timeList.get(i + 1)) {
                stopDay = i;
            }
        }
        if ((stopDay - startDay) > 0) {
            for (int i = startDay; i <= stopDay; i++) {
                resultList.set(i, resultList.get(i) + 1);
            }
        } else {
            resultList.set(startDay, resultList.get(startDay) + 1);
        }
        return resultList;
    }
}
