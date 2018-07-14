package com.github.cumt.SRS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Student;

public interface CourseService {

	/**
	 * @method 查找所有课程
	 * @param pageSize 
	 * @param pageNumber 
	 * @return HashMap
	 */
	ArrayList<Course> findAll(int pageNumber, int pageSize);

	/**
	 * @method 查询总课程数量
	 * @return int
	 */
	int countAllCourse();

	/**
	 * @method 添加课程
	 * @param course
	 * @param precourseId 
	 */
	void addCourse(Course course, String[] precourseId);

	/**
	 * @method 更新课程
	 * @param course
	 */
	void updateCourse(Course course);

	/**
	 * @method 模糊查询课程
	 * @param search
	 * @param pageSize 
	 * @param pageNumber 
	 * @return ArrayList
	 */
	ArrayList<Course> fuzzyfindCourse(String search, int pageNumber, int pageSize);

	/**
	 * @method 
	 * @param student
	 * @param semester
	 * @param pageSize 
	 * @param pageNumber 
	 * @return
	 */
	ArrayList<Course> listCourseForStudent(Student student, String semester, int pageNumber, int pageSize);

	/**
	 * @method 
	 * @param search
	 * @return int
	 */
	int countCourseForStudent(String search);

	/**
	 * @method 制定学习计划
	 * @param courseIds
	 * @param major
	 */
	void appointPlanOfStudy(List<String> courseIds, String major);
}
