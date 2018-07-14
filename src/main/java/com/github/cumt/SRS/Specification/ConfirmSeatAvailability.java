package com.github.cumt.SRS.Specification;

import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;

public class ConfirmSeatAvailability implements Specification{

	@Override
	public boolean enroll(Student student, Section section) {
		if (!section.confirmSeatAvailability()) {
			return false;
		}
		return true;
	}

}
