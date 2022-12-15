package com.skopjegeoguessr.batch.model;

import com.skopjegeoguessr.batch.model.converters.StringListConverter;
import com.skopjegeoguessr.batch.model.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "places")
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "lat")
    private float lat;

    @Column(name = "lng")
    private float lng;

    @Column(name = "name")
    private String name;

    @Transient
    @Convert(converter = StringListConverter.class)
    private List<String> types = new ArrayList<>();

    @Column(name = "photo_reference")
    private String photoReference;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
}
