package com.stage.stage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Permission")
public class Permission {

    @Id
    private String name ;

    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
