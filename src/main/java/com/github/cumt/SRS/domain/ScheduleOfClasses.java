package com.github.cumt.SRS.domain;

import java.util.HashMap;

/**
 * @describe 总课程表 
 * @time 2017年6月16日下午5:56:19
 */
public class ScheduleOfClasses {
	private String semester; // 学期
	private String courseId;
	private String sectionId;
	private HashMap<String, Section> sectionsOffered; // 提供的班次集合

	public ScheduleOfClasses(String semester) {
		this.setSemester(semester);
		sectionsOffered = new HashMap<String, Section>();
	}

	public ScheduleOfClasses(String semester, HashMap<String, Section> sectionsOffered ) {
		this.setSemester(semester);
		
		// 实例化支持空的集合
		this.sectionsOffered = sectionsOffered;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSemester() {
		return semester;
	}
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}

	
	public void display() {
		System.out.println("课程计划： " + getSemester());
		System.out.println();

		for (Section s : sectionsOffered.values()) {
			s.setOfferedIn(this);
			s.display();
			System.out.println();
		}
	}

	/**
	 * @method 添加班次
	 * @param section
	 */
	public void addSection(Section section) {
		String key = section.getRepresentedCourse().getCourseId() + 
			     " - " + section.getSectionId();
		sectionsOffered.put(key, section);

		section.setOfferedIn(this);
	}


	/**
	 * @method 查找班次
	 * @param fullSectionNo 全称
	 * @return Section
	 */
	public Section findSection(String fullSectionNo) {
		return sectionsOffered.get(fullSectionNo);
	}

	/**
	 * @method 判断提供的班次是否为空
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (sectionsOffered.size() == 0) return true;
		else return false;
	}
}
