package com.github.cumt.SRS.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cumt.SRS.dao.ScheduleDao;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;
import com.github.cumt.SRS.service.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired // 把'@Autowired'改为'@Resource(name="scheduleMockDao")'，即可切换成sqlite数据源
	protected ScheduleDao scheduleDao;
	
	ArrayList<Section> sections = new ArrayList<Section>();
	
	@Override
	public HashMap<String, Section> findBySemester(String semester) {
		
		return null;
	}

	@Override
	public ArrayList<Section> findScheduleBySemester(Teacher teacher, String semester) {
		return scheduleDao.findScheduleBySemester(teacher, semester);
	}

	@Override
	public ArrayList<Section> findScheduleForStudent(Student student, String search) {
		// TODO Auto-generated method stub
		return scheduleDao.findScheduleForStudent(student, search);
	}
}
