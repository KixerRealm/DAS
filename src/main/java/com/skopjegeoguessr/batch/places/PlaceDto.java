package com.skopjegeoguessr.batch.places;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {

    private String id;
    private float lat;
    private float lng;
    private String name;
    private List<String> types;
    private String photoReference;
}
