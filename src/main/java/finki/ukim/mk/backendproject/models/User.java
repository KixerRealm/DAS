package finki.ukim.mk.backendproject.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String picture;
}
