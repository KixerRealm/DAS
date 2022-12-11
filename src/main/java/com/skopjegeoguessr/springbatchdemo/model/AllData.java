package com.skopjegeoguessr.springbatchdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "Data")
@AllArgsConstructor
@NoArgsConstructor
public class AllData {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(generator = "strategy-uuid2")
    @GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "lat")
    private float lat;

    @Column(name = "lng")
    private float lng;

    @Column(name = "name")
    private String name;

    @Convert(converter = StringListConverter.class)
    private List<String> types = new ArrayList<>();

    @Column(name = "photoReference")
    private String photoReference;
}
