package com.github.cumt.SRS.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.PlanOfStudy;
import com.github.cumt.SRS.domain.PlanOfStudyEntry;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Transcript;
import com.github.cumt.SRS.domain.TranscriptEntry;
import com.github.cumt.SRS.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController{
	@Resource(name="studentService")
	protected StudentService studentService;
	
	// 检查账号密码是否正确
	@RequestMapping(value="/checkLogin") 
	@ResponseBody
	public Map<String, Object> checkLOgin(@RequestParam(required=true,defaultValue="0")String id,
			@RequestParam(required=true,defaultValue="0")String password, HttpSession session) throws Exception{
		// 判断账号密码是否正确
		if (studentService.checkLogin(id, password)){
			Student student = studentService.findStudentById(id);
			ArrayList<TranscriptEntry> transcriptEntries = studentService.findTranscriptForStudent(student);
			ArrayList<Course> planOfStudy = studentService.findPlanOfStudy(student);
			PlanOfStudy pOfStudy = new PlanOfStudy();
			pOfStudy.setPlanOfStudyEntry(new PlanOfStudyEntry(student,planOfStudy));
			student.setPlanOfStudy(pOfStudy);
			Transcript transcript = new Transcript(student);
			transcript.setTranscriptEntries(transcriptEntries);
			student.setTranscript(transcript);// 给学生加载课程表
			session.setAttribute("student",student); // 将student放入session
			return ajaxSuccessResponse(); // 返回正确信息
		}
		else return ajaxFailureResponse();	// 返回错误信息
	}
	
	// 检验登录并跳转页面
	@RequestMapping(value="/sendLogin") 
	@ResponseBody
	public ModelAndView sendLogin(HttpSession session){
		Student student = (Student)session.getAttribute("student"); // 从session中获取student
		// 判断session中的student是否为空
		if (student!=null)return new ModelAndView("redirect:/studentindex.jsp"); // 跳转至首页
		else return new ModelAndView("redirect:/studentlogin"); // 跳转至login页
	}
	
	// 检验登录并跳转页面
		@RequestMapping(value="/listAllstudent") 
		@ResponseBody
		public Map<String, Object> listAllstudent(HttpSession session){
			data.put("allstudent", studentService.findAllStudent());
			return data;
		}
	
	// 展示总课程表
	@RequestMapping(value="/listScheduleOfClass")
	@ResponseBody
	public Map<String, Object> listScheduleOfClass(@RequestParam(required=true,defaultValue="sm-01")String semester,
			HttpSession session){
		return data;
		
	}
	
}
