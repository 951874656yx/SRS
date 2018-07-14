package com.github.cumt.SRS.Specification;

import com.github.cumt.SRS.domain.Section;
import com.github.cumt.SRS.domain.Student;

/**
 * @describe 选课规约接口
 * @time 2017年7月3日下午10:56:31
 */
public interface Specification {
	
	/**
	 * @method 选课规约
	 * @return EnrollmentStatus
	 */
	public boolean enroll(Student student, Section section);
}
