package com.tuition.tuitionreimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.tuitionreimbursement.domain.TuitionBudget;
import com.tuition.tuitionreimbursement.service.TuitionService;
@CrossOrigin
@RestController
@RequestMapping("reservetuition")

public class TuitionController {
	@Autowired
	TuitionService tuitionService;
	
	@PostMapping("/")
	public ResponseEntity<?> reserveTuition(@RequestBody TuitionBudget tuition){
		tuitionService.addTuitionBudget(tuition);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/")
	public List<TuitionBudget> getAllTuitionsReserved(){
		return tuitionService.getAllTuitionBudgets();
	}
	
	@GetMapping("/{id}")
	public TuitionBudget getTuitionReserved(@PathVariable Long id) {
		return tuitionService.getTuitionBudgetById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateReservedTuition(@PathVariable Long id, @RequestBody TuitionBudget tuition){
		tuitionService.updateTuitionBudget(id, tuition);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReservedTuition(@PathVariable Long id){
		TuitionBudget tuition =tuitionService.getTuitionBudgetById(id);
		
		if(tuition !=null) {
			tuitionService.removeTuitionBudget(tuition);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	

}
