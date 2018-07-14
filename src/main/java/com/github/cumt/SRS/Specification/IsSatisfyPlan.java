package com.github.cumt.SRS.Specification;


import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;

public class IsSatisfyPlan implements Specification{

	@Override
	public boolean enroll(Student student, Section section) {
		if(!student.getPlanOfStudy().isInPlanOfStudy(section.getRepresentedCourse())){
			return false;
		}
		return true;
	}

}
