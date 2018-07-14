package com.github.cumt.SRS.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.cumt.SRS.domain.Course;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.TranscriptEntry;

public interface StudentService {
	
	/**
	 * @method 查找所有学生
	 * @return HashMap
	 */
	HashMap<String, Student> findAllStudent();
	
	/**
	 * @method 检查登录
	 * @param id
	 * @param password
	 * @return boolean
	 */
	boolean checkLogin(String id, String password);
	
	/**
	 * @method 通过id查找学生	 * @param id
	 * @return Student
	 */
	Student findStudentById(String id);
	
	/**
	 * @method 
	 * @param student
	 * @return
	 */
	ArrayList<TranscriptEntry> findTranscriptForStudent(Student student);

	/**
	 * @method 查询学习计划
	 * @param student
	 * @return ArrayList
	 */
	ArrayList<Course> findPlanOfStudy(Student student);
	
}
