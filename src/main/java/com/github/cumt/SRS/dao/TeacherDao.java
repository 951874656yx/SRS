package com.github.cumt.SRS.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.github.cumt.SRS.domain.Teacher;

public interface TeacherDao {
	
	/**
	 * @method 查找所有教师
	 * 	 * @return ArrayList<Teacher> 
	 */

	ArrayList<Teacher>  findAll();
	
	/**
	 * @method 根据id查询教师
	 * @param id
	 * @return Teacher
	 */
	Teacher findTeacherById(String id);
	

}
