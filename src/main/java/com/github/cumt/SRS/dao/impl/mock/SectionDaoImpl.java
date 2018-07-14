package com.github.cumt.SRS.dao.impl.mock;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.cumt.SRS.dao.CourseDao;
import com.github.cumt.SRS.dao.SectionDao;
import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;

@Repository("sectionMockDao")
public class SectionDaoImpl implements SectionDao{
	@Resource(name="courseMockDao")
	protected CourseDao courseDao;
	
	@Override
	public ArrayList<Section> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Section> findBySemester(String semester){
		// 创建HashMap对象
		ArrayList<Section> sections = new ArrayList<Section>();
		// 获取所有课程
		ArrayList<Course> listCourses = courseDao.findAll();
		HashMap<String, Course> allCourses= new HashMap<String, Course>();
		for (Course course : listCourses) {
			allCourses.put(course.getCourseId(), course);
		}
        // 创建Section变量
		Section sec1, sec2, sec3, sec4, sec5, sec6;
		// 创建Course变量
		Course c;
		
		// 为sections添加班次
		c = allCourses.get("ABC-101");
		sec1 = new Section(1,'M', "8:10 - 10:00 PM", allCourses.get("ABC-101") , "GOVT101", 30);
		sections.add(sec1);
		sec2 = new Section(2,'W', "6:10 - 8:00 PM", allCourses.get("ABC-101") , "GOVT202", 30);
		sections.add(sec2);
		
		c = allCourses.get("ABC-102");
		sec3 = new Section(1,'R', "4:10 - 6:00 PM", allCourses.get("ABC-102"), "GOVT105", 25);
		sections.add(sec3);
		sec4 = new Section(2,'T', "6:10 - 8:00 PM", allCourses.get("ABC-102"), "SCI330", 25);
		sections.add(sec4);
		
		c = allCourses.get("ABC-103");
		sec5 = new Section(1,'M', "6:10 - 8:00 PM", allCourses.get("ABC-103"), "GOVT101", 20);
		sections.add(sec5);
		
		c = allCourses.get("ABC-104");
		sec6 = new Section(1, 'R', "4:10 - 6:00 PM", c, "ABC-104", 15);
		sections.add(sec6);

		return sections;
	}

	@Override
	public ArrayList<Section>  findSectionByCourseId(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSection(Section section) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appointInstructor(Teacher teacher, String sectionId, String courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Section> findSectionBy_TreacherId(String teacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSection(Section section) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Student> findStudentBySectionId(Section section) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Section findSectionByCourseId_SectionId(String courseId, String sectionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Section> findSectionforStudent(String courseId, String sectionId, String search, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Section> findSectionByStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectSection(Student student, String s, String c, String semester) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelSection(Student student, String sectionId, String courseId, String semesterr) {
		// TODO Auto-generated method stub
		
	}


	

}
