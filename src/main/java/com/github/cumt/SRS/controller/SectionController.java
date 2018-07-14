package com.github.cumt.SRS.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.cumt.SRS.domain.EnrollmentStatus;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;
import com.github.cumt.SRS.service.SectionService;
import com.github.cumt.SRS.until.MapToList;

@Controller
@RequestMapping("/section")
public class SectionController extends BaseController{
	@Resource(name="sectionService")
	protected SectionService sectionService;
	@Autowired
	protected MapToList<Section> mapToList;
	
	@RequestMapping("/section")
	@ResponseBody
	public ModelAndView sendSection(HttpSession session) throws Exception{
		return new ModelAndView("section");
	}
	
	@RequestMapping("/teachOfSection")
	@ResponseBody
	public ModelAndView teachOfSection(HttpSession session) throws Exception{
		return new ModelAndView("teachOfSection");
	}
	
	@RequestMapping("/assignGrade")
	@ResponseBody
	public ModelAndView assignGrade(HttpSession session) throws Exception{
		return new ModelAndView("assignGrade");
	}
	
	@RequestMapping("/studentTranscript")
	@ResponseBody
	public ModelAndView studentTranscript(HttpSession session) throws Exception{
		return new ModelAndView("studentTranscript");
	}
	
	/*根据 courseId 查询班次*/
	@RequestMapping("/findSectionByCourseId")
	@ResponseBody
	public Map<String , Object> findSectionByCourseId(@RequestParam(required=true,defaultValue="0")int pageNumber,
			@RequestParam(required=true,defaultValue="5")int pageSize, HttpSession session, String courseId) throws Exception{
		
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("rows",sectionService.findSectionByCourseId(courseId));
		return map;	
	}
	
	/*根据 sectionId 查询选课学生*/
	@RequestMapping("/findStudentBySectionId")
	@ResponseBody
	public Map<String, Object> findStudentBySectionId(String sectionId, String courseId) throws Exception{
		
		data.put("total", sectionService.countStudentOfSection(sectionId, courseId));
		data.put("rows", sectionService.findStudentBySectionId(sectionId,courseId));
		return data;
	}
	
	/*添加班次*/
	@RequestMapping("/addSection")
	@ResponseBody
	public Map<String, Object> addSection(Section section, String semester, HttpSession session) throws Exception{
		
		sectionService.addSection(section ,semester);
		return ajaxSuccessResponse();
	}
	
	/*修改班次信息*/
	@RequestMapping("/updateSection")
	@ResponseBody
	public Map<String, Object> updateSection(Section section, HttpSession session) throws Exception{
		
		sectionService.updateSection(section);
		return ajaxSuccessResponse();
	}
	
	/*指定授课教师*/
	@RequestMapping("/appointInstructor")
	@ResponseBody
	public Map<String, Object> appointInstructor(HttpSession session, String sectionId, String courseId) throws Exception{
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if (sectionService.appointInstructor(teacher,sectionId,courseId)) return ajaxSuccessResponse();
		else return ajaxFailureResponse();
	}
	
	/*注销授课*/
	@RequestMapping("/cancelInstructor")
	@ResponseBody
	public Map<String, Object> cancelInstructor(HttpSession session, String sectionId, String courseId) throws Exception{
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		sectionService.cancelInstructor(teacher, sectionId, courseId);
		return data;
	}
	
	/*查询需要指派成绩的学生*/
	@RequestMapping("/findStudentForGrade")
	@ResponseBody
	public Map<String, Object> findStudentForGrade(@RequestParam(required=true,defaultValue="0")int pageNumber,
			@RequestParam(required=true,defaultValue="0")int pageSize,HttpSession session, String search,
			String courseId, String sectionId) throws Exception{
		data.put("total", sectionService.countStudentForGrade(courseId, sectionId, search));
		data.put("rows", sectionService.findStudentForGrade(courseId, sectionId, search, pageNumber, pageSize));
		return data;
	}
	
	/*指派成绩*/
	@RequestMapping("/appointGrade")
	@ResponseBody
	public Map<String, Object> appointGrade(String courseId, String sectionId, String studentId, String grade) throws Exception{
		sectionService.appointGrade(courseId, sectionId, studentId, grade);
		return ajaxSuccessResponse();
	}
	
	/*查询学生选课班次*/
	@RequestMapping("/findSectionforStudent")
	@ResponseBody
	public Map<String, Object> findSectionforStudent(String courseId, String sectionId, String search, HttpSession session) throws Exception{
		Student student = (Student)session.getAttribute("student");
		data.put("rows", sectionService.findSectionforStudent(courseId, sectionId, search, student));
		return data;
	}
	
	/*学生选课*/
	@RequestMapping("/appointSection")
	@ResponseBody
	public Map<String, Object> appointSection(HttpSession session, String sectionId, String courseId, String semester ) throws Exception{
		
		Student student = (Student)session.getAttribute("student");
		EnrollmentStatus enrollmentStatus = sectionService.appointSection(student, sectionId, courseId);
		if (enrollmentStatus.equals(EnrollmentStatus.success)) {
			sectionService.selectSection(student, sectionId, courseId, semester);
			System.out.println("45646465465465");
		}
		session.setAttribute("student", student);
		return ajaxSuccessResponse(enrollmentStatus.value());
	}
	
	/*退选课程*/
	@RequestMapping("/cancelSection")
	@ResponseBody
	public Map<String,Object> cancelSection(HttpSession session, String sectionId, String courseId, String semester) throws Exception{
		
		Student student = (Student)session.getAttribute("student");
		if (sectionService.cancelSection(student,sectionId,courseId,semester)){
			session.setAttribute("student", student);
			return ajaxSuccessResponse();
		}
		else return ajaxFailureResponse();
	}
	
	/*查看成绩单*/
	@RequestMapping("/findTranscriptForStudent")
	@ResponseBody
	public Map<String, Object> findTranscriptForStudent(HttpSession session) throws Exception{
		Student student = (Student)session.getAttribute("student");
		data.put("rows",student.getTranscript().getTranscriptEntries());
		return data;
	}
}  
