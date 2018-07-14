package com.github.cumt.SRS.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @since Jdk1.8
 * @describe 课程类
 * @time 2017年6月16日下午4:52:39
 */
public class Course {
	
	private String courseId;// 课程编号
	private String courseName;// 课程名称
	private double credits;// 学分
	private ArrayList<Section> offeredSection;// 提供的课程班次集合
	private ArrayList<Course> prerequisites;// 课程的前置课程
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {

		this.credits = credits;
	}
	
	public void setPrerequisites(ArrayList<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public Course(){
		
	}
	
	public Course(String courseId, String courseName, double credits) {
		super();
		this.setCourseId(courseId);
		this.setCourseName(courseName);
		this.setCredits(credits);
		
		offeredSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}
	
	/**
	 * @method 打印课程信息到console
	 */
	public void display() {
		System.out.println("课程信息:");
		System.out.println("\t课程编号:  " + getCourseId());
		System.out.println("\t课程名称:  " + getCourseName());
		System.out.println("\t课程学分:  " + getCredits());
		System.out.println("\t前置课程:");

		for (Course c : prerequisites) {
			System.out.println("\t\t" + c.toString());
		}

		// Note use of print vs. println in next line of code.

//		System.out.print("\tOffered As Section(s):  ");
//		for (Section s : offeredSection) {
//			System.out.print(s.getSectionNo() + " ");
//		}

		// Finish with a blank line.

		System.out.println();
	}
	
	@Override
	public String toString() {
		return getCourseId() + ":  " + getCourseName();
	}
	
	/**
	 * @method 添加前置课程
	 * @param course
	 */
	public void addPrerequisite(Course course) {
		prerequisites.add(course);
	}

	/**
	 * @method 是否拥有前置课程
	 * @return boolean
	 */
	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) return true;
		else return false;
	}

	/**
	 * @method 获取所有前置课程
	 * @return Collection
	 */
	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}

	/**
	 * @method 安排课程时间表
	 * @param day
	 * @param time
	 * @param room
	 * @param capacity 容量
	 * @return Section
	 */
	public Section scheduleSection(char day, String time, String room,int capacity) {
		// 创建一个新的班次对象
		Section s = new Section(offeredSection.size() + 1, day, time, this, room, capacity);
		// 添加班次
		addSection(s);
		return s;
	}

	/**
	 * @method 添加班次
	 * @param section
	 */
	public void addSection(Section section) {
		offeredSection.add(section);
	}
}
