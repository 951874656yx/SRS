package com.github.cumt.SRS.service;

import java.util.HashMap;


import com.github.cumt.SRS.domain.Teacher;

public interface TeacherService {
	
	/**
	 * @method 查找所有教师
 * @return HashMap
	 */
	HashMap<String, Teacher> findAll();
	
	/**
	 * @method 检查登录
	 * @param id
	 * @param password
	 * @return boolean
	 */
	boolean checkLogin(String id, String password);
	
	/**
	 * @method 通过id查找教师
	 * @param id
	 * @return Teacher
	 */
	Teacher findTeacherById(String id);
}
