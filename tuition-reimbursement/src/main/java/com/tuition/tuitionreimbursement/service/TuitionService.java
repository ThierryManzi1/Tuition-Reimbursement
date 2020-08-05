package com.tuition.tuitionreimbursement.service;

import java.util.List;


import com.tuition.tuitionreimbursement.domain.TuitionBudget;

public interface TuitionService {
	public void addTuitionBudget(TuitionBudget tuition);
	public List<TuitionBudget> getAllTuitionBudgets();
	public TuitionBudget updateTuitionBudget(Long id, TuitionBudget tuition);
	public String removeTuitionBudget(TuitionBudget tuition);
	public TuitionBudget getTuitionBudgetById(Long id);
}
