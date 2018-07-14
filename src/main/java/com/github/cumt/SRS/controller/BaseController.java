package com.github.cumt.SRS.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @describe 基础controller
 * @time 2017年6月1日下午5:26:02
 */
public class BaseController {
	Map<String, Object> data =new HashMap<String,Object>();
	ModelAndView mView=new ModelAndView();
	
	protected Map<String, Object> ajaxSuccessResponse(){
		HashMap<String, Object> result=new HashMap<String ,Object>();
		result.put("success", true);
		return result;
	}
	
	protected Map<String,Object> ajaxSuccessResponse(String msg){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",true);
		if(StringUtils.isNotBlank(msg)&&StringUtils.isNotEmpty(msg)){
			result.put("msg",msg);
		}
		return result;
	}
	
	protected Map<String,Object> ajaxFailureResponse(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",false);
		return result;
	}
	
	protected Map<String,Object> ajaxFailureResponse(String msg){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success",false);
		result.put("msg", msg);
		return result;
	}
	 
}
