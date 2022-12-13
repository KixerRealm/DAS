package finki.ukim.mk.backendproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guess {


    @Id
    private String id;

    private String location;

    private String image;

    public Guess() {}

    public Guess(String id, String location, String image) {
        this.id = id;
        this.location = location;
        this.image = image;
    }
}
