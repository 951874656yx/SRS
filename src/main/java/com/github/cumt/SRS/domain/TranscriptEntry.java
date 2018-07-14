package com.github.cumt.SRS.domain;

/**
 * @describe 指派成绩
 * @time 2017年6月16日下午5:57:57
 */
public class TranscriptEntry {
	private String grade;
	private Student student;
	private Section section;
	private Transcript transcript;

	public TranscriptEntry(){
		
	}
	public TranscriptEntry(Student student, String grade, Section section) {
		this.setStudent(student);
		this.setSection(section);
		this.setGrade(grade);

		Transcript transcript = student.getTranscript();
		this.setTranscript(transcript);
		transcript.addTranscriptEntry(this);
	}

	public void setStudent(Student s) {
		student = s;
	}

	public Student getStudent() {
		return student;
	}

	public void setSection(Section s) {
		section = s;
	}

	public Section getSection() {
		return section;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}


	/**
	 * @method 验证成绩等级正确
	 * @param grade
	 * @return boolean
	 */
	public static boolean validateGrade(String grade) {
		boolean outcome = false;

		if (grade.equals("F") ||
		    grade.equals("I")) {
			outcome = true;
		}
		if (grade.startsWith("A") ||
		    grade.startsWith("B") ||
		    grade.startsWith("C") ||
		    grade.startsWith("D")) {
			if (grade.length() == 1) outcome = true;
			else if (grade.length() == 2) {
				if (grade.endsWith("+") ||
				    grade.endsWith("-")) {
					outcome = true;
				}
			}
		}

		return outcome;
	}

	/**
	 * @method 验证成绩是否通过
	 * @param grade
	 * @return boolean
	 */
	public static boolean passingGrade(String grade) {
		boolean outcome = false;
		
		// 验证成绩等级是否正确
		if (validateGrade(grade)) {
			
			// 判断等级是否合格
			if (grade.startsWith("A") ||
			    grade.startsWith("B") ||
			    grade.startsWith("C") ||
			    grade.startsWith("D")) {
				outcome = true;
			}
		}
		return outcome;
	}
}
