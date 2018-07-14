package com.github.cumt.SRS.domain;

import java.util.ArrayList;

/**

 * @describe 教师类
 * @time 2017年6月16日下午4:16:36
 */
public class Teacher extends Person{
	
	private String title; // 职称
	private String department; // 部门
	private ArrayList<Section> teach; // 教授的课程班次集合
	
	
	public ArrayList<Section> getTeach() {
		return teach;
	}

	public void setTeach(ArrayList<Section> teach) {
		this.teach = teach;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}

	
	public Teacher(String id, String name,String password, String title, String department) {
		super(id, name, password);
		this.setTitle(title);
		this.setDepartment(department);
		teach=new ArrayList<Section>();
	}
	
	public Teacher() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName() + " (" + getTitle() + ", " +
			       getDepartment() + ")";
	}
	
	@Override
	public void display(){
		super.display();
		System.out.println("教师具体信息：");
		System.out.println("\t职称： "+this.getTitle());
		System.out.println("\t部门： "+this.getDepartment());
		displayTeachingAssignments();
		System.out.println();
	}


	/**
	 * @method 打印教学任务到console
	 */
	public void displayTeachingAssignments() {
		// TODO Auto-generated method stub
		System.out.println("教学任务： " +getName());
		if (teach.size() == 0) {
			System.out.println("\t(没有教学任务)");
		}
		else for (Section s : teach) {
			System.out.println("\t课程编号.:  " +
				s.getRepresentedCourse().getCourseId());
			System.out.println("\t班次编号.:  " + 
				s.getSectionId());
			System.out.println("\t课程名称:  " +
				s.getRepresentedCourse().getCourseName());
			System.out.println("\t时间:  " +
				s.getDayOfWeek() + " - " + 
				s.getTimeOfDay());
			System.out.println("\t-----");
		}
	}
	
	/**
	 * @method 教师同意教授某课程
	 * @param section
	 */
	public void agreeToTeach(Section section){
		teach.add(section);
		// 给section添加授课教师
		section.setInstructor(this);
	}
	
	
	/**
	 * @method 是否教授了该班次
	 * @param section
	 * @return boolean
	 */
	public boolean isInTeached(Section section){
		for (Section section1 : teach) {
			if (section1.getFullSectionId().equals(section.getFullSectionId())) return true;
		}
		return false;
	}
	
}
