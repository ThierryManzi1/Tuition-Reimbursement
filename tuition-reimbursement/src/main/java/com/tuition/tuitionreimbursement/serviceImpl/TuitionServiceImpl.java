package com.tuition.tuitionreimbursement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuition.tuitionreimbursement.domain.TuitionBudget;
import com.tuition.tuitionreimbursement.repository.TuitionBudgetRepository;
import com.tuition.tuitionreimbursement.service.TuitionService;

@Service
public class TuitionServiceImpl implements TuitionService{
	@Autowired
	TuitionBudgetRepository tuitionBudgetRepository;

	public void addTuitionBudget(TuitionBudget tuition) {
		tuition.setActive(true);
		tuitionBudgetRepository.save(tuition);
		
	}

	public List<TuitionBudget> getAllTuitionBudgets() {
		//return tuitionBudgetRepository.findAll();
		return tuitionBudgetRepository.findByIsActiveTrue();
	}

	public TuitionBudget updateTuitionBudget(Long id, TuitionBudget tuition) {
		TuitionBudget currentTuitionBudget = tuitionBudgetRepository.getOne(id);
		currentTuitionBudget.setSchool(tuition.getSchool());
		currentTuitionBudget.setSemester(tuition.getSemester());
		tuitionBudgetRepository.save(currentTuitionBudget);
		return currentTuitionBudget;
	}

	
	public String removeTuitionBudget(TuitionBudget tuition) {
		try {
			//tuitionBudgetRepository.delete(tuition);
			tuition.setActive(false);
			tuitionBudgetRepository.save(tuition);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "Tuition Budget has been successfully removed";
	}

	
	public TuitionBudget getTuitionBudgetById(Long id) {
		return tuitionBudgetRepository.getOne(id);
	}

}
