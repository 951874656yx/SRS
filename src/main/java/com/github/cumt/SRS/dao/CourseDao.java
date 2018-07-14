package com.github.cumt.SRS.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Student;

public interface CourseDao {
	
	/**
	 * @method 查找所有课程
	 * @return ArrayList
	 */
	ArrayList<Course> findAll();

	/**
	 * @method 查询总课程数量
	 * @return int
	 */
	int countAllCourse();

	/**
	 * @method 添加课程
	 * @param course
	 */
	void addCourse(Course course);

	/**
	 * @method 更新课程
	 * @param course
	 */
	void updateCourse(Course course);

	/**
	 * @method 模糊查询课程
	 * @param search
	 * @return ArrayList
	 */
	ArrayList<Course> fuzzyfindCourse(String search);

	/**
	 * @method 添加前置课程
	 * @param courseId
	 * @param precoursId
	 */
	void addPreCourse(@Param("courseId")String courseId, @Param("precourseId")String precoursId);

	/**
	 * @method 
	 * @param student
	 * @param semester
	 * @return ArrayList
	 */
	ArrayList<Course> listCourseForStudent(@Param("student")Student student, @Param("semester")String semester);

	/**
	 * @method 
	 * @param semester
	 * @return
	 */
	int countCourseForStudent(String semester);

	/**
	 * @method 
	 * @param courseId
	 * @return
	 */
	ArrayList<Course> findPreCourse(String courseId);

	/**
	 * @method 制定学习计划
	 * @param courseIds
	 * @param major
	 */
	void appointPlanOfStudy(@Param("courseIds")List<String> courseIds, @Param("major")String major);

	/**
	 * @method 查询学习计划
	 * @param student
	 * @return ArrayList
	 */
	ArrayList<Course> findPlanOfStudy(@Param("student")Student student);
	
	
}
