package com.stage.stage.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.stage.stage.document.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stage.stage.document.User;
import com.stage.stage.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	
	@Autowired
	UserRepository userRepo ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		List<User> users = userRepo.findAll();
		MyUserDetails myUserDetails = null ; 

		User myUser = null ;

		//Retrive teh authenticaated User
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				myUser = user ;
				break ;
			}
		}

		//Retrive Permisions associated to this this user
		Collection<Permission> myUser_permissions = myUser.getRole().getPermissions();

		//Create authority for each permission
		List<GrantedAuthority> permissions = new ArrayList<>();
		for (Permission p : myUser_permissions) {
			permissions.add(new SimpleGrantedAuthority(p.getName()));
		}

		// Create UserDetails object
		myUserDetails = new MyUserDetails(permissions, username, myUser.getPassword(),
				 true, true, true, true ) ;

		return myUserDetails ;
	}

}
