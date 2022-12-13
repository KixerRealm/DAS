package finki.ukim.mk.backendproject.models;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//@Entity
public class User {

//    @NotNull
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String user_id;

    private String username;

    private String password;

    private String email;

    private String picture;

    public User(String user_id, String username, String password, String email, String picture) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public User() {
    }
}
