package com.zhaxd.core.dto;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultDto {

	/**
     * 定义返回值状态
     */
    private String status;
    /**
     * 返回值数据
     */
    private Object data;
    /**
     * 返回值失败携带提示信息
     */
    private String message;

    /**
     * jackson进行json序列化工具
     */
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 序列化中的时间格式化
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public ResultDto() { }
    
    public ResultDto(String status) { 
    	this.status = status;
    }

    private ResultDto(String status, Object data){
        this.status = status;
        this.data = data;
    }
    
    private ResultDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 返回成功，返回列表，需要自定义时间格式化
     * @param status 返回值状态
     * @param data 返回的列表数据
     * @param pageable 返回的列表携带的分页数据（需要和前端框架中的分页参数进行协商好）
     * @param DateFormat 返回的列表需要时间格式化的自定义格式
     * @return JSON字符串
     */
    public static String resultListWithFormat(String status, Object data, String DateFormat) {
        try {
            objectMapper.setDateFormat(new SimpleDateFormat(DateFormat));
            return objectMapper.writeValueAsString(new ResultDto(status, data));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回成功，返回列表，默认时间格式化
     * @param status 返回值状态
     * @param data 返回值列表数据
     * @param pageable 返回列表携带的分页数据
     * @return JSON字符串
     */
    public static String resultList(String status, Object data) {
        try {
            objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            return objectMapper.writeValueAsString(new ResultDto(status, data));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回成功，返回单个实体
     * @param data 单个实体的数据
     * @return JSON字符串
     */
    public static String success(Object data) {
        try {
            objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            return objectMapper.writeValueAsString(new ResultDto("success", data));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回成功，没有参数
     * @return JSON字符串
     */
    public static String success() {
        try {
            return objectMapper.writeValueAsString(new ResultDto("success"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回失败
     * @param message 返回失败的提示信息
     * @return JSON字符串
     */
    public static String fail(String message) {
        try {
            return objectMapper.writeValueAsString(new ResultDto("error", message));
        } catch (Exception e) {
            return null;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}