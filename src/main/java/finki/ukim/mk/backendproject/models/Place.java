package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "places")
public class Place {

    @Id
    private String id;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "name")
    private String location_name;

    @Column(name = "photoReference")
    private String photo_reference;

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type")
    private PlaceType type;
}
