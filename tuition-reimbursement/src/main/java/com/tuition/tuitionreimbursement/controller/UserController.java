package com.tuition.tuitionreimbursement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuition.tuitionreimbursement.domain.User;
import com.tuition.tuitionreimbursement.serviceImpl.MyUserDetailsService;

@RestController
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	MyUserDetailsService userService;
	
	@PostMapping("/")
	public User registration (@RequestBody User user) throws Exception {
		userService.save(user);
		return user;
	}

}
