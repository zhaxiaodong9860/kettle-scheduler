package com.zhaxd.web.controller;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.model.KUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhaxd.core.dto.ResultDto;
import com.zhaxd.web.service.UserService;
import com.zhaxd.web.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("getList.shtml")
	public String getList(Integer offset, Integer limit){
		return JsonUtils.objectToJson(userService.getList(offset, limit));
	}
	
	@RequestMapping("delete.shtml")
	public String delete(Integer uId){
		userService.delete(uId);
		return ResultDto.success();
	}
	
	@RequestMapping("resetPassword.shtml")
	public String resetPassword(){
		
		return ResultDto.success();
	}
	@RequestMapping("insert.shtml")
	public String insert(KUser kNewUser, HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		userService.insert(kNewUser, kUser.getuId());
		return ResultDto.success();
	}
	@RequestMapping("IsAccountExist.shtml")
	public void IsAccountExist(String uAccount, HttpServletResponse response) {
		try {
			if (userService.IsAccountExist(uAccount)) {
				response.getWriter().write("false");
			} else {
				response.getWriter().write("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("getUser.shtml")
	public String getQuartz(Integer uId){
		return ResultDto.success(userService.getUser(uId));
	}
	@RequestMapping("update.shtml")
	public String update(KUser kUser, HttpServletRequest request){
		KUser kLoginUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		try{
			userService.update(kUser, kLoginUser.getuId());
			return ResultDto.success();
		}catch(Exception e){
			return ResultDto.success(e.toString());
		}
	}
}
