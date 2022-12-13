package finki.ukim.mk.backendproject.models;

import finki.ukim.mk.backendproject.enumerators.GameType;
import finki.ukim.mk.backendproject.enumerators.PlaceType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Place {
    @Id
    private String id;

    private Float lat;

    private Float lng;

    private String location_name;

    private String photo_reference;

    private PlaceType type;

    public Place(){}

    public Place(String id, Float lat, Float lng, String location_name, String photo_reference, PlaceType type) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.location_name = location_name;
        this.photo_reference = photo_reference;
        this.type = type;
    }
}
