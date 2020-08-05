package com.tuition.tuitionreimbursement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tuition.tuitionreimbursement.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class TuitionReimbursementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuitionReimbursementApplication.class, args);
	}

}
