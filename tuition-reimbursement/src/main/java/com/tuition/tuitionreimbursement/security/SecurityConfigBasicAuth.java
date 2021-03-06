package com.tuition.tuitionreimbursement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfigBasicAuth extends WebSecurityConfigurerAdapter {
	
	  @Autowired
	    UserDetailsService userDetailsService;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .cors().and()
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/grades/**").hasAnyRole("ADMIN", "USER")
	        //.antMatchers("/basicauth").hasAnyRole("ADMIN", "USER")
	        .antMatchers("/users/**").permitAll()
	        .antMatchers("/register/**").permitAll()
	        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	        	.anyRequest().authenticated()
	        	.and()
	        .httpBasic();
	    }

	    @Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

}
