package com.tuition.tuitionreimbursement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.tuitionreimbursement.domain.AuthenticationBean;

@CrossOrigin
@RestController
public class BasicAuthController {
	@GetMapping(path="/basicauth")
	public AuthenticationBean authenticated() {
		return new AuthenticationBean("You are authenticated");
	}
}
