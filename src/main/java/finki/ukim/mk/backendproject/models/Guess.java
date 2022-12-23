package finki.ukim.mk.backendproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "guesses")
public class Guess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guess_id")
    private String id;

    @Column(name = "guess_place")
    private String place;

    @Column(name = "image_url")
    private String image_url;

}
