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
}
