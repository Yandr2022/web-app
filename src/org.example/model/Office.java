package org.example.model;

import lombok.Data;

@Data
public class Office {
    private  int id;
    private String name;
    private String location;
    private String phone;
    private String fax;
}
/*
    SELECT u.`name`, `email`, `password`, `isActive`, `createdTime`, `updatedTime`, o.name, o.location, o.phone,
     o.fax FROM `users_db` AS u JOIN offices AS o WHERE u.officeId = o.id and email = "sharkievich@gmail.com";*/
