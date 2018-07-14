package com.github.cumt.SRS.domain;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cumt.SRS.Specification.ConfirmSeatAvailability;
import com.github.cumt.SRS.Specification.IsCurrentlyEnrolledSimilar;
import com.github.cumt.SRS.Specification.IsSatisfyPlan;
import com.github.cumt.SRS.Specification.IsSatisfyPreCourse;
import com.github.cumt.SRS.Specification.Specification;
import com.github.cumt.SRS.dao.SectionDao;
import com.github.cumt.SRS.service.SectionService;

/**
 * @since Jdk1.8
 * @describe 班次类
 * @time 2017年6月16日下午4:52:54
 */
@Component
public class Section {
	@Resource(name="sectionService") 
	protected SectionService sectionService;
	
	private int sectionId; // 班次编号
	private char dayOfWeek; // 星期几
	private String timeOfDay; // 一天的时间
	private String room; // 教室
	private int seatingCapacity; // 座位
	private Course representedCourse; // 代表的课程
	private ScheduleOfClasses offeredIn; // 提供的课程表
	private Teacher instructor; // 指导教师
	private Student student;
	private String studentId;
	private HashMap<String, Student> enrolledStudents; // 参加班次的学生
	private HashMap<Student, TranscriptEntry> assignedGrades; // 成绩等级
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public char getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(char dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public Course getRepresentedCourse() {
		return representedCourse;
	}
	public void setRepresentedCourse(Course representedCourse) {
		this.representedCourse = representedCourse;
	}
	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}
	public void setOfferedIn(ScheduleOfClasses offeredIn) {
		this.offeredIn = offeredIn;
	}
	public Teacher getInstructor() {
		return instructor;
	}
	public void setInstructor(Teacher instructor) {
		this.instructor = instructor;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public HashMap<String, Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(HashMap<String, Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public Section(){
		
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Section(int sectionId, char dayOfWeek, String timeOfDay,Course representedCourse, String room, int seatingCapacity
			) {
		super();
		this.setSectionId(sectionId);
		this.setDayOfWeek(dayOfWeek);
		this.setTimeOfDay(timeOfDay);
		this.setRoom(room);
		this.setSeatingCapacity(seatingCapacity);
		this.setRepresentedCourse(representedCourse);
		this.setInstructor(null);
		enrolledStudents = new HashMap<String,Student>();
		assignedGrades = new HashMap<Student,TranscriptEntry>();
	}
	
	@Override
	public String toString() {
		return getRepresentedCourse().getCourseId() + " - " +
		       getSectionId() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}
	
	/**
	 * @method 获取班次的编号
	 * @return String
	 */
	public String getFullSectionId() {
		return getRepresentedCourse().getCourseId() + 
		       " - " + getSectionId();
	}
	
	/**
	 * @method 获取班次的信息
	 * @return String
	 */
	public String getFullSectionInfo() {
		return getRepresentedCourse().getCourseName() + 
			   "-" + getDayOfWeek() + "-" +
		       "" + getTimeOfDay() +
		       "-" + this.getRoom();
	}
	
	/**
	 * @method 验证是否能参加该班次课程
	 * @param student
	 * @return EnrollmentStatus
	 */
	public EnrollmentStatus enroll(Student student) {
		// 取出规约
		Specification isSimilar = new IsCurrentlyEnrolledSimilar();
		Specification isSatisfyPreCourse = new IsSatisfyPreCourse();
	//	Specification isSatisfyPlan = new IsSatisfyPlan();
		Specification confirmSeatAvailability = new ConfirmSeatAvailability();
		
		if (!isSimilar.enroll(student, this)) return EnrollmentStatus.prevEnroll; // 判断是否参加过类似的课程
		
		else if (!isSatisfyPreCourse.enroll(student, this)) return EnrollmentStatus.prereq; // 判断是否满足前置课程
			
	//	else if (!isSatisfyPlan.enroll(student, this)) return EnrollmentStatus.outOfPlan; // 是否满足学习计划
			
		else if (!confirmSeatAvailability.enroll(student, this)) return EnrollmentStatus.secFull; // 是否有座位
		
		// 双向绑定
		enrolledStudents.put(student.getId(), student);
		student.addSection(this);
		return EnrollmentStatus.success;
	}
	
	/**
	 * @method 获取成绩等级
	 * @param student
	 * @return String
	 */
	public String getGrade(Student student) {
		String grade = null;

		
		TranscriptEntry transcriptEntry = assignedGrades.get(student);
		if (transcriptEntry != null) {
			grade = transcriptEntry.getGrade(); 
		}

		return grade;
	}
	
	/**
	 * @method 确定座位是否已满
	 * @return boolean
	 */
	public boolean confirmSeatAvailability() {
		if (enrolledStudents.size() < getSeatingCapacity()) return true;
		else return false;
	}

	/**
	 * @method 退选课程
	 * @param student
	 * @return boolean
	 */
	public boolean drop(Student student) {
	
		if (!student.isEnrolledIn(this)) return false;
		else {
			// 从参加课程的学生中移除
			enrolledStudents.remove(student.getId());
			student.dropSection(this);
			return true;
		}
	}

	/**
	 * @method 获取参加班次学生的总数
	 * @return int
	 */
	public int reachTotalEnrollment() {
		return enrolledStudents.size();
	}	

	public void display() {
		System.out.println("班次信息:");
		System.out.println("\t学期:  " + getOfferedIn().getSemester());
		System.out.println("\t课程编号.:  " + 
				   getRepresentedCourse().getCourseId());
		System.out.println("\t班次编号:  " + getSectionId());
		System.out.println("\t时间与日期:  " + getDayOfWeek() + 
				   " at " + getTimeOfDay());
		System.out.println("\t教室:  " + getRoom());
		if (getInstructor() != null) 
			System.out.println("\t授课老师:  " + 
				   getInstructor().getName());
		displayStudentRoster();
	}
	
	public void displayStudentRoster() {
		System.out.print("\tTotal of " + reachTotalEnrollment() + 
				   " students enrolled");
		if (reachTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");
		for (Student s : enrolledStudents.values()) {
			System.out.println("\t\t" + s.getName());
		}
	}
	
	public boolean postGrade(Student student, String grade) {
		// 判断成绩等级是否正确.
		if (!TranscriptEntry.validateGrade(grade)) return false;
		
		// 判断学生成绩是否已存在
		if (assignedGrades.get(student) != null) return false;

		// 生成成绩
		TranscriptEntry te = new TranscriptEntry(student, grade, this);

		// 添加成绩到assignedGrade
		assignedGrades.put(student, te);

		return true;
	}
	
	/**
	 * @method 验证该班次是否属于该课程
	 * @param course
	 * @return boolean
	 */
	public boolean isSectionOf(Course course) {
		if (course.getCourseId().equals(representedCourse.getCourseId())) return true;
		else return false;
	}
}
