package com.github.cumt.SRS.domain;

import java.util.ArrayList;
import java.util.Collection;



/**
 * @describe 学生类
 * @time 2017年6月16日下午2:46:02
 */
public class Student extends Person{
	private String grade;
	private String major;	
	private Transcript transcript; // 成绩单
	private ArrayList<Section> sections; // 课程班次集合
	private PlanOfStudy planOfStudy; // 学习计划
	
	
	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	public PlanOfStudy getPlanOfStudy() {
		return planOfStudy;
	}

	public void setPlanOfStudy(PlanOfStudy planOfStudy) {
		this.planOfStudy = planOfStudy;
	}

	public Student(){
		
	}

	public Student(String id, String name, String password, String grade, String major) {
		super(id,name,password);
		this.grade = grade;
		this.major = major;
		/*创建一个新的学习计划*/
		this.setPlanOfStudy(new PlanOfStudy(this));
		
		/*创建一个新的成绩单*/
		this.setTranscript(new Transcript(this));
		
		/*实例化集合*/
		sections = new ArrayList<Section>();
		
	}	
	
	/**
	 * @method 重用构造函数
	 * @param id
	 */
	public Student(String id){
		this(id, "", "", "", "");
	}

	@Override
	public String toString() {
		
		return this.getName() + " (" + this.getId() + ") [" + this.getGrade() +
			       " - " + this.getMajor() + "]";
	}
	
	@Override
	public void display(){
		System.out.println("学生具体信息： ");
		System.out.println("\t年级: "+this.getGrade());
		System.out.println("\t专业: "+this.getMajor());
		this.printCourseSchedule();
		this.printTranscript();
		System.out.println();
	}

	/**
	 * @method 打印成绩单到console
	 */
	private void printTranscript() {
		
		this.getTranscript().display();
	}

	/**
	 * @method 打印课程表到console
	 */
	private void printCourseSchedule() {
		
		System.out.println("学生课程表 " + this.getName());
		for (Section s : sections) {            
			if (s.getGrade(this) == null) {
				System.out.println("\t课程编号.:  " + 
					s.getRepresentedCourse().getCourseId());
				System.out.println("\t班次编号.:  " + 
					s.getSectionId());
				System.out.println("\t班次名称:  " + 
					s.getRepresentedCourse().getCourseName());
				System.out.println("\t时间:  "  +
					s.getDayOfWeek() + " - " +
					s.getTimeOfDay());
				System.out.println("\t教室:  "  +
					s.getRoom());
				System.out.println("\t教师姓名:  " +
					s.getInstructor().getName());
				System.out.println("\t-----");
		    }
		}
	}
	
	/**
	 * @method 参加课程班次
	 * @param section
	 */
	public void addSection(Section section){
		sections.add(section);
	}
	
	/**
	 * @method 退出课程班次
	 * @param section
	 */
	public void dropSection(Section section){
		sections.remove(section);
	}
	
	/**
	 * @method 是否参加了指定的课程
	 * @param section
	 * @return boolean
	 */
	public boolean isEnrolledIn(Section section){
		for (Section section1 : sections) {
			if (section.getFullSectionId().equals(section1.getFullSectionId())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @method 当前是否参加了类似的课程（班次不同，课程相同）
	 * @param section
	 * @returns boolean
	 */
	public boolean isCurrentlyEnrolledSimilar(Section section){
		boolean foundMatch = false;
		Course c1 = section.getRepresentedCourse();
		for (Section section1 : sections) {
			Course c2 = section1.getRepresentedCourse();
			if (c1 == c2) {
				
				// 如果课程一样
				if (section1.getGrade(this) == null) {
					
					// 如果课程成绩为null
					foundMatch = true;
					break;
				}
			}
			}
			return foundMatch;
	}
	
	/**
	 * @method 获取当前学生参加的课程班次
	 * @return Collection<Section>
	 */
	public Collection<Section> getEnrolledSections(){
		return sections;
	}




}
