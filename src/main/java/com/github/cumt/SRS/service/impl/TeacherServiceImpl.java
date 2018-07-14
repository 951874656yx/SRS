package com.github.cumt.SRS.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cumt.SRS.dao.SectionDao;
import com.github.cumt.SRS.dao.TeacherDao;
import com.github.cumt.SRS.domain.Teacher;
import com.github.cumt.SRS.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{
	@Autowired // 把@Autowired改为@Resource(name="teacherMockDao")，即可切换成sqlite数据源
	protected TeacherDao teacherDao;
	@Autowired
	protected SectionDao sectionDao;
	
	HashMap<String, Teacher> mapTeacher;
	ArrayList<Teacher> listTeacher;
	
	@Override
	public HashMap<String, Teacher> findAll() {
		mapTeacher = new HashMap<String, Teacher>();
		for (Teacher teacher : teacherDao.findAll()) {
			mapTeacher.put(teacher.getId(), teacher);
		}
		return mapTeacher;
	}

	@Override
	public boolean checkLogin(String id, String password) {
		if (teacherDao.findTeacherById(id) != null && password.equals(teacherDao.findTeacherById(id).getPassword())) return true; 
		else return false;
	}

	@Override
	public Teacher findTeacherById(String id) {
		Teacher teacher =teacherDao.findTeacherById(id);
		teacher.setTeach(sectionDao.findSectionBy_TreacherId(id));
		return teacher;
	}

}
