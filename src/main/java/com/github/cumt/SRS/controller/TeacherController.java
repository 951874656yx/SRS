package com.github.cumt.SRS.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.cumt.SRS.domain.Teacher;
import com.github.cumt.SRS.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController{
	@Resource(name="teacherService")
	protected TeacherService teacherService;
	
	
	@RequestMapping(value="/teacher_checkLogin")
	@ResponseBody
	public Map<String, Object> teacher_checkLogin(@RequestParam(required=true, defaultValue="0")String id,
			@RequestParam(required=true, defaultValue="0")String password, HttpSession session){
		// 判断账号密码是否正确
		if (teacherService.checkLogin(id, password)) {
			session.setAttribute("teacher", teacherService.findTeacherById(id));
			return ajaxSuccessResponse();
		}
		else return ajaxFailureResponse();	
	}
	
	@RequestMapping("/teacher_sendLogin")
	@ResponseBody
	public ModelAndView teacher_sendLogin(HttpSession session){
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher!=null)return new ModelAndView("redirect:/teacherindex.jsp");
		else return new ModelAndView("redirect:/teacherlogin.jsp");
	}
	
	
}
