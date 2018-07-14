package com.github.cumt.SRS.service;


import java.util.ArrayList;
import java.util.HashMap;

import com.github.cumt.SRS.domain.EnrollmentStatus;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;

public interface SectionService {
	
	/**
	 * @method 根据学期查询总课程表
	 * @param semester
	 * @return HashMap
	 */
	HashMap<String ,Section> findBySemester(String semester);

	/**
	 * @method 根据courseId查询班次
	 * @param courseId
	 * @return ArrayList<Section>
	 */
	ArrayList<Section> findSectionByCourseId(String courseId);
	
	/**
	 * @method 根据courseId查询班次
	 * @param courseId
	 * @return ArrayList<Section>
	 */
	Section findSectionByCourseId(String courseId ,String sectionId);

	/**
	 * @method 添加课程
	 * @param section
	 * @param semester 
	 */
	void addSection(Section section, String semester);

	/**
	 * @method 指派授课教师
	 * @param teacher
	 * @param section
	 * @return
	 */
	boolean appointInstructor(Teacher teacher, String sectionId, String courseId);

	/**
	 * @method 更新班次信息
	 * @param section
	 */
	void updateSection(Section section);

	/**
	 * @method 查询参加班次的学生
	 * @param sectionId
	 * @return ArrayList
	 */
	ArrayList<Student> findStudentBySectionId(String sectionId, String courseId);

	/**
	 * @method 注销授课
	 * @param teacher
	 * @param sectionId
	 * @param courseId
	 */
	void cancelInstructor(Teacher teacher, String sectionId, String courseId);

	/**
	 * @method 统计参与班次的学生总数
	 * @param sectionId
	 * @param courseId
	 * @return int
	 */
	int countStudentOfSection(String sectionId, String courseId);

	/**
	 * @method 
	 * @param courseId
	 * @param semester
	 * @return ArrayList
	 */
	ArrayList<Student> findStudentForGrade(String courseId, String sectionId, String semester, int pageNumber, int pageSize);

	/**
	 * @method 
	 * @param courseId
	 * @param sectionId
	 * @param search
	 * @param pageSize 
	 * @param pageNumber 
	 * @return
	 */
	int countStudentForGrade(String courseId, String sectionId, String search);

	
	/**
	 * @method 
	 * @param courseId
	 * @param sectionId
	 * @param studentId
	 * @param grade
	 */
	void appointGrade(String courseId, String sectionId, String studentId, String grade);

	/**
	 * @method 
	 * @param courseId
	 * @param sectionId
	 * @param search
	 * @param student
	 * @return
	 */
	ArrayList<Section> findSectionforStudent(String courseId, String sectionId, String search, Student student);

	/**
	 * @method 学生选课
	 * @param student
	 * @param sectionId
	 * @param courseId
	 * @return boolean
	 */
	EnrollmentStatus appointSection(Student student, String sectionId, String courseId);
	
	/**
	 * @method 保存选课
	 * @param student
	 * @param sectionId
	 * @param courseId
	 * @param semester
	 */
	void selectSection(Student student, String sectionId, String courseId, String semester);

	/**
	 * @method 退选课程
	 * @param student
	 * @param sectionId
	 * @param courseId
	 * @param semester
	 * @return boolean
	 */
	boolean cancelSection(Student student, String sectionId, String courseId, String semester);
}
