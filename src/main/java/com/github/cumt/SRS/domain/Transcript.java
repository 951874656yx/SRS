package com.github.cumt.SRS.domain;

import java.util.ArrayList;

/**
 * @describe 成绩单类
 * @time 2017年6月18日下午1:34:22
 */
public class Transcript {

	private ArrayList<TranscriptEntry> transcriptEntries; // 成绩集合
	private Student studentOwner; // 学生
	
	
	
	public ArrayList<TranscriptEntry> getTranscriptEntries() {
		return transcriptEntries;
	}

	public void setTranscriptEntries(ArrayList<TranscriptEntry> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}
	
	public Transcript(Student student) {
		setStudentOwner(student);
		transcriptEntries = new ArrayList<TranscriptEntry>();
	}

	public void setStudentOwner(Student s) {
		studentOwner = s;
	}
	public Student getStudentOwner() {
		return studentOwner;
	}

	/**
	 * @method 确认是否完成课程
	 * @param course
	 * @return boolean
	 */
	public boolean verifyCompletion(Course course) {
		boolean outcome = false;

		for (TranscriptEntry te : transcriptEntries) {
			Section section = te.getSection();
			
			// 判断班次是否属于课程
			if (section.isSectionOf(course)) {
			    // 判断成绩是否通过

			    if (TranscriptEntry.passingGrade(te.getGrade())) {
				outcome = true;
				break;
			    }
			}
		}
		return outcome;
	}

	/**
	 * @method 添加成绩
	 * @param transcriptEntry
	 */
	public void addTranscriptEntry(TranscriptEntry transcriptEntry) {
		transcriptEntries.add(transcriptEntry);
	}

	public void display() {
		System.out.println("学生个人成绩单:  " +
				   getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(无成绩)");
		}
		else for (TranscriptEntry te : transcriptEntries) {
			Section sec = te.getSection();

			Course c = sec.getRepresentedCourse();

			ScheduleOfClasses soc = sec.getOfferedIn();

			System.out.println("\t学期:        " +
					   soc.getSemester());
			System.out.println("\t课程编号.:      " +
					   c.getCourseId());
			System.out.println("\t学分:         " +
					   c.getCredits());
			System.out.println("\t获得成绩:  " +
					   te.getGrade());
			System.out.println("\t-----");
		}
	}

}
