package org.example.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;


@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Set<Role> roles;//TODO POPULATE via jdbc
    private Office office;

    public boolean isActive() {
        return isActive;
    }
    public boolean getIsActive() {
        return isActive;
    }
}
