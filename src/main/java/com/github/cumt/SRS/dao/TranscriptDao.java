package com.github.cumt.SRS.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.github.cumt.SRS.domain.Student;
import com.github.cumt.SRS.domain.TranscriptEntry;

public interface TranscriptDao {
	
	ArrayList<TranscriptEntry> findTranscriptForStudent(@Param("student")Student student);
}
