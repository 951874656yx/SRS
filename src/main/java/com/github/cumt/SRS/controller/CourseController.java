package com.github.cumt.SRS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.service.CourseService;
import com.github.cumt.SRS.until.MapToList;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController{
	@Resource(name="courseService")
	protected CourseService courseService;
	@Autowired
	protected MapToList<Course> mapToList;
	
	// 跳转页面
		@RequestMapping("/course")
		@ResponseBody
		public ModelAndView sendCourse(HttpSession session) throws Exception{
			return new ModelAndView("course");
		}
		
		@RequestMapping("/selectCourse")
		@ResponseBody
		public ModelAndView selectCourse(HttpSession session) throws Exception{
			return new ModelAndView("selectCourse");
		}
		
		@RequestMapping("/addPlanOfStudy")
		@ResponseBody
		public ModelAndView addPlanOfStudy() throws Exception{
			return new ModelAndView("addPlanOfStudy");
		}
	
	// 输出所有课程
	@RequestMapping(value="/listAllCourse")
	@ResponseBody
	public Map<String, Object> listAllCourse(@RequestParam(required=true,defaultValue="0")int pageNumber,
			@RequestParam(required=true,defaultValue="0")int pageSize,HttpSession session,String search) throws Exception{
		if (search.equals("")) {
			data.put("total", courseService.countAllCourse()); // 插入总课程数
			data.put("rows", courseService.findAll(pageNumber, pageSize)); // 插入课程信息
		}
		else {
			String searchText = new StringBuilder("%").append(search).append("%").toString();
			ArrayList<Course> courses = courseService.fuzzyfindCourse(searchText,pageNumber,pageSize);
			data.put("total", courses.size());
			data.put("rows", courses);
		}
		return data;	
	}
	
	// 添加课程
	@RequestMapping(value="/addCourse",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCourse(Course course,@RequestParam( required=true, defaultValue="")String[] precourseId, 
			HttpSession session) throws Exception{
		if (course!=null) {
			courseService.addCourse(course,precourseId);
			return ajaxSuccessResponse();
		}
		else return ajaxFailureResponse();
	}
	
	// 更新课程信息
	@RequestMapping("/updateCourse")
	@ResponseBody
	public Map<String, Object> updateCourse(String asc,Course course) throws Exception{
		if (course!=null) {
			courseService.updateCourse(course);
			return ajaxSuccessResponse();
		}
		else return ajaxFailureResponse();
	}
	
	// 输出选课总课程
	@RequestMapping("/listCourseForStudent")
	@ResponseBody
	public Map<String, Object> listCourseForStudent(@RequestParam(required=true,defaultValue="0")int pageNumber,
			@RequestParam(required=true,defaultValue="0")int pageSize,HttpSession session, String search) throws Exception{
		Student student = (Student)session.getAttribute("student");
		data.put("total", courseService.countCourseForStudent(search));
		data.put("rows", courseService.listCourseForStudent(student, search, pageNumber, pageSize ));
		return data;
	}
	
	/*制定学习计划*/
	@RequestMapping("/appointPlanOfStudy")
	@ResponseBody
	public Map<String, Object> appointPlanOfStudy( @RequestParam(value = "courseIds[]") ArrayList<String>  courseIds, String major, HttpSession session){
		courseService.appointPlanOfStudy(courseIds,major);
		return ajaxSuccessResponse();
	}
}
