package com.github.cumt.SRS.domain;


/**
 * @describe 学习计划
 * @time 2017年6月19日下午3:10:58
 */
public class PlanOfStudy {
	private Student student; // 学生
	private PlanOfStudyEntry planOfStudyEntry;
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public PlanOfStudyEntry getPlanOfStudyEntry() {
		return planOfStudyEntry;
	}

	public void setPlanOfStudyEntry(PlanOfStudyEntry planOfStudyEntry) {
		this.planOfStudyEntry = planOfStudyEntry;
	}
	
	public PlanOfStudy(){
		
	}
	
	public PlanOfStudy(Student student){
		this.setStudent(student);
	}
	
	public boolean isInPlanOfStudy(Course course){
		for (Course course2 : planOfStudyEntry.getCourseOffered()) {
			if (course.getCourseId().equals(course2.getCourseId())) return true;
		}
		return false;
	} 
}
