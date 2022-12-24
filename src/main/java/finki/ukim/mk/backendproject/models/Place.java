package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "places")
public class Place {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "lat")
    private float lat;

    @Column(name = "lng")
    private float lng;

    @Column(name = "name")
    private String location_name;

    @Column(name = "photo_reference")
    private String photo_reference;

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type")
    private PlaceType type;
}
