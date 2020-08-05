package com.tuition.tuitionreimbursement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuition.tuitionreimbursement.domain.MyUserDetails;
import com.tuition.tuitionreimbursement.domain.User;
import com.tuition.tuitionreimbursement.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
    
    public void save(User user) throws Exception {
    	if (!user.getPasswordConfirm().equals(user.getPassword())) {
            throw new Exception("Passowrds don't match");
        }
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setActive(true);
    	if(user.getRoles().equals("admin")) {
    		user.setRoles("ROLE_ADMIN");
    	}else {
    		user.setRoles("ROLE_USER");
    	}
    	userRepository.save(user);
    }
}