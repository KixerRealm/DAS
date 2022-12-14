package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;
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

//    public Place(){}
//
//    public Place(String id, Float lat, Float lng, String location_name, String photo_reference, PlaceType type) {
//        this.id = id;
//        this.lat = lat;
//        this.lng = lng;
//        this.location_name = location_name;
//        this.photo_reference = photo_reference;
//        this.type = type;
//    }
}
