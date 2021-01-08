package com.stage.stage;


import com.stage.stage.document.Permission;
import com.stage.stage.document.Role;
import com.stage.stage.repository.PermissionRepository;
import com.stage.stage.repository.RoleRepository;
import com.stage.stage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@SpringBootApplication
public class StageApplication implements CommandLineRunner {

//	@Autowired
//	RoleRepository roleRepo ;
//
//	@Autowired
//	PermissionRepository permRepo ;


	
	public static void main(String[] args) {
		SpringApplication.run(StageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		//----Define and persist all permissions---------
//		Permission addProd = new Permission("ADD_PRODUCT");
//		Permission editProd = new Permission("EDIT_PRODUCT");
//		Permission deleteProd = new Permission("DELETE_PRODUCT");
//
//		Permission addUser = new Permission("ADD_USER");
//		Permission editUser = new Permission("EDIT_USER");
//		Permission deletePUser = new Permission("DELETE_USER");
//
//		permRepo.saveAll(Arrays.asList(addProd , editProd ,deleteProd , addUser , editUser , deletePUser));
//
//
//		//----Define and persist all roles---------------
//		Collection<Permission> userPermissions = Arrays.asList(addProd , editProd ,deleteProd);
//		Role userRole = new Role("ROLE_USER" , userPermissions);
//
//		Collection<Permission> adminPermissions = Arrays.asList(addProd , editProd ,deleteProd , addUser , editUser , deletePUser );
//		Role adminRole = new Role("ROLE_ADMIN" , adminPermissions);
//
//		roleRepo.saveAll(Arrays.asList(adminRole , userRole));
	}
}
