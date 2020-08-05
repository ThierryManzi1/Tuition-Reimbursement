package com.tuition.tuitionreimbursement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tuition.tuitionreimbursement.domain.Grade;

public interface GradeService {
	public Grade storeTranscript(String mark,MultipartFile transcript);
	public Grade getTranscript(Long id) ;
	public List<Grade> getAllTuitionGrades();
	public String removeTuitionGrade(Grade grade);
	public Grade getGradeById(Long id);
	public Grade updateGrade(Long id,String mark, MultipartFile transcript);
	
	
}
