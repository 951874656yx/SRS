package com.github.cumt.SRS.dao.impl.mock;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.github.cumt.SRS.dao.TeacherDao;
import com.github.cumt.SRS.domain.Teacher;

@Repository("teacherMockDao")
public class TeacherDaoImpl implements TeacherDao{
	
	HashMap<String, Teacher> mapTeacher;
	ArrayList<Teacher> listTeacher;
	
	@Override
	public ArrayList<Teacher> findAll() {
		// 创建HashMap对象
		listTeacher = new ArrayList<Teacher>();
		// 创建Teacher变量
		Teacher tc1,tc2,tc3;
		// 创建teacher 对象
		tc1 = new Teacher("T09153699", "陈教授", "123456", "副教授", "数学");
		tc2 = new Teacher("T09153737", "张教授", "123456", "副教授", "市场营销");
		tc3 = new Teacher("T09153719", "杨教授", "123456", "副教授", "信息系统");
		
		// 为listAllTeacher添加数据
		listTeacher.add(tc1);
		listTeacher.add(tc2);
		listTeacher.add(tc3);
		return listTeacher;
	}

	@Override
	public Teacher findTeacherById(String id){
		for (Teacher teacher : findAll()) {
			if (teacher.getId().equals(id)) return teacher;
		}
		return null;
	}
	
	
}
