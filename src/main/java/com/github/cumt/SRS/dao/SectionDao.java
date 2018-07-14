package com.github.cumt.SRS.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;

public interface SectionDao {
	
	/**
	 * @method 获取所有班次
	 * @return ArrayList<Section>
	 */
	ArrayList<Section> findAll();
	
	/**
	 * @method 根据学期获取班次
	 * @param semester
	 * @return ArrayList
	 */
	ArrayList<Section> findBySemester(String semester);

	/**
	 * @method 根据courseId查询班次
	 * 	 * @param courseId
	 * @return HashMap
	 */
	ArrayList<Section>  findSectionByCourseId(String courseId);

	/**
	 * @method 添加课程
	 * @param section
	 */
	void addSection(Section section);

	/**
	 * @method 指派授课教师
	 * @param teacher
	 * @param section
	 */
	void appointInstructor(@Param("teacher")Teacher teacher, @Param("sectionId")String sectionId, @Param("courseId")String courseId);
	
	/**
	 * @method 查询教师授课的班次
	 * @param teacherId
	 * @return ArrayList
	 */
	ArrayList<Section> findSectionBy_TreacherId(String teacherId);

	/**
	 * @method 更新课程
	 * @param section
	 */
	void updateSection(Section section);

	/**
	 * @method 根据班次查询学生
	 * @param section
	 * @return ArrayList
	 */
	ArrayList<Student> findStudentBySectionId(Section section);

	/**
	 * @method 查询具体班次
	 * @param courseId
	 * @param sectionId
	 * @return Section
	 */
	Section findSectionByCourseId_SectionId(@Param("courseId")String courseId, @Param("sectionId")String sectionId);

	/**
	 * @method 
	 * @param courseId
	 * @param sectionId
	 * @param search
	 * @param student
	 * @return ArrayList<Section>
	 */
	ArrayList<Section> findSectionforStudent(@Param("courseId")String courseId, @Param("sectionId")String sectionId,
			@Param("semester")String search, @Param("student")Student student);

	/**
	 * @method 查询学生所选所有课程
	 * @param student
	 * @return ArrayList
	 */
	ArrayList<Section> findSectionByStudent(@Param("student")Student student);

	/**
	 * @method 学生选课
	 * @param student
	 * @param semester 
	 * @param section
	 */
	void selectSection(@Param("student")Student student, @Param("sectionId")String sectionId, @Param("courseId")String courseId, @Param("semester")String semester);

	/**
	 * @method 退选课程
	 * @param student
	 * @param sectionId
	 * @param courseId
	 * @param semester
	 */
	void cancelSection(@Param("student")Student student, @Param("sectionId")String sectionId, @Param("courseId")String courseId, @Param("semester")String semesterr);

	
}
