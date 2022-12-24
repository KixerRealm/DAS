package finki.ukim.mk.backendproject.models;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "accounts")
public class Account {

    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @Column(name = "image_url")
    private String profilePictureUrl;

    @OneToMany
    private List<LeaderboardRecord> recordList;

    @OneToMany
    private List<Game> games;

}
