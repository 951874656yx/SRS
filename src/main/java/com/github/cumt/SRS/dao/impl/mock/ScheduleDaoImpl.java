package com.github.cumt.SRS.dao.impl.mock;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.cumt.SRS.dao.ScheduleDao;
import com.github.cumt.SRS.dao.SectionDao;
import com.github.cumt.SRS.domain.ScheduleOfClasses;
import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Teacher;

@Repository("scheduleMockDao")
public class ScheduleDaoImpl implements ScheduleDao{
	@Resource(name="sectionMockDao")
	protected SectionDao sectionDao;
	@Override
	public ScheduleOfClasses findScheduleOfClasses(String smaster) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addSchedule(Section section,String semester) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Section> findScheduleBySemester(Teacher teacher, String semester) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Section> findScheduleForStudent(Student student, String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
