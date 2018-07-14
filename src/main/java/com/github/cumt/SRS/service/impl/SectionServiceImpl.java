package com.github.cumt.SRS.service.impl;

import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cumt.SRS.dao.CourseDao;
import com.github.cumt.SRS.dao.ScheduleDao;
import com.github.cumt.SRS.dao.SectionDao;
import com.github.cumt.SRS.dao.StudentDao;
import com.github.cumt.SRS.dao.TranscriptDao;
import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.EnrollmentStatus;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;
import com.github.cumt.SRS.domain.Transcript;
import com.github.cumt.SRS.domain.TranscriptEntry;
import com.github.cumt.SRS.service.SectionService;
import com.github.pagehelper.PageHelper;

@Service("sectionService")
public class SectionServiceImpl implements SectionService{
	@Autowired // 把'@Autowired'改为'@Resource(name="sectionMockDao")'，即可切换成sqlite数据源
	protected SectionDao sectionDao;
	@Autowired
	protected StudentDao studentDao;
	@Autowired 
	protected ScheduleDao scheduleDao;
	@Autowired
	protected TranscriptDao transcriptDao;
	@Autowired
	protected CourseDao courseDao;
	
	ArrayList<Section> listSections = new ArrayList<Section>();
	HashMap<String, Section> mapSections= new HashMap<String, Section>();
	HashMap<String, Student> studentMap = new HashMap<String, Student>();
	
	HashMap<String, Section> finAll(){
		return null;
		
	}

	@Override
	public HashMap<String, Section> findBySemester(String semester) {
		listSections = sectionDao.findBySemester(semester);
		for (Section section : listSections) {
			mapSections.put(section.getRepresentedCourse().getCourseId()+"-"+section.getSectionId(), section);
		}
		return mapSections;
	}

	@Override
	public ArrayList<Section> findSectionByCourseId(String courseId) {
		listSections = sectionDao.findSectionByCourseId(courseId);
//		for (Section section : listSections) {
//			mapSections.put(section.getRepresentedCourse().getCourseId()+"-"+section.getSectionId(), section);
//		}
		return listSections;
	}

	@Override
	public void addSection(Section section, String semester) {
		sectionDao.addSection(section);
		scheduleDao.addSchedule(section,semester);
	}

	@Override
	public boolean appointInstructor(Teacher teacher, String sectionId, String courseId) {
		if (teacher.isInTeached(findSectionByCourseId(courseId,sectionId))) return false;
		else sectionDao.appointInstructor(teacher, sectionId, courseId);
		return true;
	}

	@Override
	public void updateSection(Section section) {
		sectionDao.updateSection(section);
	}

	@Override
	public ArrayList<Student> findStudentBySectionId(String sectionId, String dayOfWeek) {
		return studentDao.findStudentBySectionId(sectionId,dayOfWeek);
	}

	@Override
	public Section findSectionByCourseId(String courseId, String sectionId) {
		HashMap<String, Student> studentMap = new HashMap<String, Student>();
		Section section = sectionDao.findSectionByCourseId_SectionId(courseId, sectionId);
		ArrayList<Course> courses = courseDao.findPreCourse(courseId);
		ArrayList<Student> students= studentDao.findStudentBySectionId(sectionId, courseId);
		for (Student student2 : students) {
			studentMap.put(student2.getId(), student2);
		}
		section.getRepresentedCourse().setPrerequisites(courses);
		section.setEnrolledStudents(studentMap);
		return section;
	}

	@Override
	public void cancelInstructor(Teacher teacher, String sectionId, String courseId) {
		
	}

	@Override
	public int countStudentOfSection(String sectionId, String courseId) {
		return studentDao.countStudentOfSection(sectionId, courseId);
	}

	@Override
	public ArrayList<Student> findStudentForGrade(String courseId, String sectionId, String semester, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		return studentDao.findStudentForGrade(courseId, sectionId, semester);
		
	}

	@Override
	public int countStudentForGrade(String courseId, String sectionId, String search) {
		
		return studentDao.countStudentForGrade(courseId, sectionId, search);
	}

	@Override
	public void appointGrade(String courseId, String sectionId, String studentId, String grade) {
		studentDao.appointGrade(courseId, sectionId, studentId, grade);
	}

	@Override
	public ArrayList<Section> findSectionforStudent(String courseId, String sectionId, String search, Student student) {
		return sectionDao.findSectionforStudent(courseId, sectionId, search, student);
	}

	@Override
	public EnrollmentStatus appointSection(Student student, String sectionId, String courseId) {
		Section section=findSectionByCourseId(courseId, sectionId);
		student.setSections(sectionDao.findSectionByStudent(student));
		section.setStudent(student);
		return section.enroll(student);
	}

	@Override
	public void selectSection(Student student, String sectionId, String courseId, String semester) {
		
		sectionDao.selectSection(student, sectionId, courseId, semester);
	}

	@Override
	public boolean cancelSection(Student student, String sectionId, String courseId, String semester) {
		Section section=findSectionByCourseId(courseId, sectionId);
		student.setSections(sectionDao.findSectionByStudent(student));
		section.setStudent(student);
		if(section.drop(student)){
			sectionDao.cancelSection(student, sectionId, courseId, semester);
			return true;
		}
		return false;
	}
}
