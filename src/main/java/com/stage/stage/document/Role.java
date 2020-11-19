package com.stage.stage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "Role")
public class Role {

    @Id
    private String name ;
    private Collection<Permission> permissions ;

    public Role() {}

    public Role( String name, Collection<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
