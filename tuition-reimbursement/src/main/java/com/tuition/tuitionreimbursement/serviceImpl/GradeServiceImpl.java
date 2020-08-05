package com.tuition.tuitionreimbursement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.tuition.tuitionreimbursement.domain.Grade;
import com.tuition.tuitionreimbursement.domain.TuitionBudget;
import com.tuition.tuitionreimbursement.exception.FileNotFoundException;
import com.tuition.tuitionreimbursement.exception.FileStorageException;
import com.tuition.tuitionreimbursement.repository.GradeRepository;
import com.tuition.tuitionreimbursement.service.GradeService;


@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	GradeRepository gradeRepository;

	@Override
	public Grade storeTranscript(String mark, MultipartFile transcript ) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(transcript.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Grade grade = new Grade(mark, fileName, transcript.getContentType(), transcript.getBytes());
            grade.setActive(true);
			return gradeRepository.save(grade);
		}catch(Exception e ) {
			throw new FileStorageException("could not store file "+fileName);
		}
		
	}

	@Override
	public Grade getTranscript(Long id) {
		
		return gradeRepository.findById(id).orElseThrow(()-> new FileNotFoundException("File not found with id"+id));
	}

	@Override
	public List<Grade> getAllTuitionGrades() {
		//return gradeRepository.findAll();
		return gradeRepository.findByIsActiveTrue();
	}

	@Override
	public String removeTuitionGrade(Grade grade) {
		try {
			//gradeRepository.delete(grade);
			grade.setActive(false);
			gradeRepository.save(grade);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Grade getGradeById(Long id) {
		return gradeRepository.getOne(id);
	}
		
	//public TuitionBudget updateTuitionBudget(Long id, TuitionBudget tuition)
	public Grade updateGrade(Long id,String mark, MultipartFile transcript) {
		Grade currentGrade = gradeRepository.getOne(id);
		String fileName = StringUtils.cleanPath(transcript.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            currentGrade.setMark(mark);
            currentGrade.setFileName(fileName);
            currentGrade.setFileType(transcript.getContentType());
            currentGrade.setData(transcript.getBytes());
            
			return gradeRepository.save(currentGrade);
		}catch(Exception e ) {
			throw new FileStorageException("could not store file "+fileName);
		}
		
	}
	

}
