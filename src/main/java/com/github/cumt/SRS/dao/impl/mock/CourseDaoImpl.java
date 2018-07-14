package com.github.cumt.SRS.dao.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.cumt.SRS.dao.CourseDao;
import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Student;

@Repository("courseMockDao")
public class CourseDaoImpl implements CourseDao{
	
	@Override
	public ArrayList<Course> findAll() {
		// 创建新的hashMap容器
		ArrayList<Course>listAllCourse = new ArrayList<Course>();

		// 创建Course 对象
		Course c1 = new Course("ABC-101", "信息系统分析与设计（上）", 2.5);
		Course c2 = new Course("ABC-102", "信息系统分析与设计（下）", 2.5);
		Course c3 = new Course("ABC-103", "网络营销", 2.5);
		Course c4 = new Course("ABC-104", "高等数学（上）", 2.5);
		
		// 为course对象添加前置课程
		c1.addPrerequisite(c4);
		c2.addPrerequisite(c1);
		c3.addPrerequisite(c2);
		
		// 为hashmap对象加载数据
		listAllCourse.add(c1);
		listAllCourse.add(c2);
		listAllCourse.add(c3);
		listAllCourse.add(c4);
		return listAllCourse;
	}

	@Override
	public int countAllCourse() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Course> fuzzyfindCourse(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPreCourse(String courseId, String precoursId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Course> listCourseForStudent(Student student, String semester) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countCourseForStudent(String semester) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Course> findPreCourse(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void appointPlanOfStudy(List<String> courseIds, String major) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Course> findPlanOfStudy(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
