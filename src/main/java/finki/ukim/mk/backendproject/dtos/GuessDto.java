package finki.ukim.mk.backendproject.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuessDto {
    private String place;
    private String image_url;
}
