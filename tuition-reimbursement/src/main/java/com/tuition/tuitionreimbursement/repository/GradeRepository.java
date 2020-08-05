package com.tuition.tuitionreimbursement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuition.tuitionreimbursement.domain.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
	List<Grade>findByIsActiveTrue();
}
