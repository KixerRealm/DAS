package com.skopjegeoguessr.springbatchdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllDataDto {

    //@JsonProperty("id")
    private String id;

    //@JsonProperty("lat")
    private float lat;

    //@JsonProperty("lng")
    private float lng;

    //@JsonProperty("name")
    private String name;

    //@JsonProperty("types")
    private List<String> types;

    //@JsonProperty("photoReference")
    private String photoReference;
}
