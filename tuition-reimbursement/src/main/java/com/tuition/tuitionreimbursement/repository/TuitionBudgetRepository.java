package com.tuition.tuitionreimbursement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuition.tuitionreimbursement.domain.TuitionBudget;

@Repository
public interface TuitionBudgetRepository extends JpaRepository<TuitionBudget, Long> {
	
	List<TuitionBudget>findByIsActiveTrue();

}
