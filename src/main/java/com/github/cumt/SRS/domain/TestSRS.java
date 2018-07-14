package com.github.cumt.SRS.domain;

import java.util.ArrayList;

public class TestSRS {
	public static ArrayList<Teacher> faculty;
	public static ArrayList<Student> studentBody;
	public static ArrayList<Course> courseCatalog; 

	public static ScheduleOfClasses scheduleOfClasses =
		      new ScheduleOfClasses("2015-1");
	
	public static void main(String[] args) {
		Teacher p1, p2, p3;
		Student s1, s2, s3;
		Course c1, c2, c3, c4, c5;
		Section sec1;
        Section sec2;
        Section sec3;
        Section sec4;
        Section sec5;
        Section sec6;
        Section sec7;

        p1 = new Teacher("T09153699","张老师", "123456",
				   "教授", "信息技术");

		p2 = new Teacher("T09153737","刘老师", "123456",
				   "教授", "数学");

		p3 = new Teacher("T09153719","陈老师", "123456",
				   "教授", "英语");


		faculty = new ArrayList<Teacher>();
		faculty.add(p1);
		faculty.add(p2);
		faculty.add(p3);

		
		s1 = new Student("09153699","燕鑫", "123456", "2015", "电子商务");

		s2 = new Student("09153737","李静玉", "123456", "2015", "电子商务");

		s3 = new Student("09153719","张旭敏", "123456", "2015", "电子商务");


		studentBody = new ArrayList<Student>();
		studentBody.add(s1);
		studentBody.add(s2);
		studentBody.add(s3);

		
		c1 = new Course("ABC-101",
				"高等数学", 3.0);

		c2 = new Course("ABC-102", 
				"大学英语", 3.0);

		c3 = new Course("ABC-103", 
				"网络营销", 3.0);

		c4 = new Course("ABC-104",
				"信息系统", 3.0);

		c5 = new Course("ABC-105",
				"不知道什么课程", 3.0);


		courseCatalog = new ArrayList<Course>();
		courseCatalog.add(c1);
		courseCatalog.add(c2);
		courseCatalog.add(c3);
		courseCatalog.add(c4);
		courseCatalog.add(c5);
		
		c5.addPrerequisite(c1);

		sec1 = c1.scheduleSection('M', "8:10 - 10:00 PM", "GOVT101", 0);

		sec2 = c1.scheduleSection('W', "6:10 - 8:00 PM", "GOVT202", 30);

		sec3 = c2.scheduleSection('R', "4:10 - 6:00 PM", "GOVT105", 25);

		sec4 = c2.scheduleSection('T', "6:10 - 8:00 PM", "SCI330", 25);

		sec5 = c3.scheduleSection('M', "6:10 - 8:00 PM", "GOVT101", 20);

		sec6 = c4.scheduleSection('R', "4:10 - 6:00 PM", "SCI241", 15);
		
		sec7 = c5.scheduleSection('M', "4:10 - 6:00 PM", "ARTS25", 40);
		

		scheduleOfClasses.addSection(sec1);
		scheduleOfClasses.addSection(sec2);
		scheduleOfClasses.addSection(sec3);
		scheduleOfClasses.addSection(sec4);
		scheduleOfClasses.addSection(sec5);
		scheduleOfClasses.addSection(sec6);
		scheduleOfClasses.addSection(sec7);


		p3.agreeToTeach(sec1);
		p2.agreeToTeach(sec2);
		p1.agreeToTeach(sec3);
		p3.agreeToTeach(sec4);
		p1.agreeToTeach(sec5);
		p2.agreeToTeach(sec6);
		p3.agreeToTeach(sec7);

		System.out.println("===============================");
		System.out.println("Student registration has begun!");
		System.out.println("===============================");
		System.out.println();

		System.out.println("Student " + s1.getName() + 
				   " is attempting to enroll in " +
				   sec1.toString());

		EnrollmentStatus status = sec1.enroll(s1);
		reportStatus(status);
		attemptToEnroll(s1, sec2);

		attemptToEnroll(s1, sec2);
	
		attemptToEnroll(s2, sec3);
		
		attemptToEnroll(s2, sec7);
		
		attemptToEnroll(s3, sec1);


		sec1.postGrade(s1, "C+");
		sec1.postGrade(s3, "A");
		sec2.postGrade(s2, "B+");
		sec7.postGrade(s2, "A-");
	
		System.out.println("====================");
		System.out.println("Schedule of Classes:");
		System.out.println("====================");
		System.out.println();
		scheduleOfClasses.display();

		System.out.println("=======");
		System.out.println("教授信息:");
		System.out.println("=======");
		System.out.println();
		p1.display();
		p2.display();
		p3.display();

		System.out.println("=======");
		System.out.println("学生信息:");
		System.out.println("=======");
		System.out.println();
		s1.display();
		s2.display();
		s3.display();
	}


	private static void reportStatus(EnrollmentStatus s) {
		System.out.println("Status:  " + s.value());
		System.out.println();
	}


	private static void attemptToEnroll(Student s, Section sec) {
		System.out.println("Student " + s.getName() + 
				   " is attempting to enroll in " +
				   sec.toString());

		reportStatus(sec.enroll(s));
	}
}
