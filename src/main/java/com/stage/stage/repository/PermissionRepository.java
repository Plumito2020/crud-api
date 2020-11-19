package com.stage.stage.repository;

import com.stage.stage.document.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
