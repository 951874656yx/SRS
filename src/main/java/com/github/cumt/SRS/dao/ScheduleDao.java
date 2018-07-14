package com.github.cumt.SRS.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.github.cumt.SRS.domain.ScheduleOfClasses;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;

public interface ScheduleDao{
	
	/**
	 * @method 获取总课程表
	 * @param smaster
	 * @return ScheduleOfClasses
	 */
	ScheduleOfClasses findScheduleOfClasses(String smaster);

	/**
	 * @method 添加课程表
	 * @param section 
	 * @param semester
	 */
	void addSchedule(@Param("section")Section section, @Param("semester")String semester);

	/**
	 * @method 查询教师课程表
	 * @param teacher
	 * @param semester
	 * @return ArrayList
	 */
	ArrayList<Section> findScheduleBySemester(@Param("teacher")Teacher teacher, @Param("semester")String semester);

	/**
	 * @method 
	 * @param student
	 * @param search
	 * @return
	 */
	ArrayList<Section> findScheduleForStudent(@Param("student")Student student, @Param("search")String search);
	
	

}
