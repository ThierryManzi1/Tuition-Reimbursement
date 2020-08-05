package com.tuition.tuitionreimbursement.controller;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tuition.tuitionreimbursement.domain.Grade;
import com.tuition.tuitionreimbursement.domain.TuitionBudget;
import com.tuition.tuitionreimbursement.payload.UploadResponse;
import com.tuition.tuitionreimbursement.service.GradeService;

import ch.qos.logback.classic.Logger;
@CrossOrigin
@RestController
@RequestMapping("/grades")
public class GradeController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(GradeController.class);
	@Autowired
	GradeService gradeService;
	
	@PostMapping("/uploadTranscript")
	public UploadResponse uploadTranscript( @RequestParam("grade")String mark, @RequestParam("file") MultipartFile transcript) {
		
		Grade grade= gradeService.storeTranscript(mark,transcript);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadTranscript/")
					.toUriString();
		
		return new UploadResponse(grade.getFileName(), fileDownloadUri, transcript.getContentType(), transcript.getSize());
	}
//	@PostMapping("/uploadMultipleTranscripts")
//	public List<UploadResponse> uploadMultipleTranscripts(@PathVariable MultipartFile[] transcripts)throws Exception{
//		       
//		return Arrays.asList(transcripts)
//				.stream()
//				.map(transcript-> uploadTranscript (transcript))
//				.collect(Collectors.toList());
//	}
	@GetMapping("/downloadTranscript/{id}")
	public ResponseEntity<ByteArrayResource> downloadTranscript(@PathVariable Long id){
		//Load file from Database
		Grade transcript = gradeService.getTranscript(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(transcript.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+transcript.getFileName()+"\"")
				.body(new ByteArrayResource(transcript.getData()));
	}
	@GetMapping("/")
	public List<Grade> getAllGrades(){
		return gradeService.getAllTuitionGrades();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable Long id){
		Grade grade = gradeService.getGradeById(id);
		if(grade!=null) {
			gradeService.removeTuitionGrade(grade);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/uploadTranscript/{id}")
	public UploadResponse updateGrade(@PathVariable Long id, @RequestParam("grade")String mark, @RequestParam("file") MultipartFile transcript) {
		
		Grade grade= gradeService.updateGrade(id, mark, transcript);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadTranscript/")
					.toUriString();
		
		return new UploadResponse(grade.getFileName(), fileDownloadUri, transcript.getContentType(), transcript.getSize());
	}
	
}
