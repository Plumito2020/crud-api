package com.stage.stage.repository;

import com.stage.stage.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
