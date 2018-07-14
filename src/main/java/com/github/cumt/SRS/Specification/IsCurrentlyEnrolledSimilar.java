package com.github.cumt.SRS.Specification;

import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.Transcript;

public class IsCurrentlyEnrolledSimilar implements Specification{

	@Override
	public boolean enroll(Student student, Section section) {
		Transcript transcript = student.getTranscript();
		if (student.isCurrentlyEnrolledSimilar(section) || 
			    transcript.verifyCompletion(section.getRepresentedCourse())) {
				return false;
			}
		return true;
	}

}
