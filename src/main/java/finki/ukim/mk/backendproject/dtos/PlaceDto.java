package finki.ukim.mk.backendproject.dtos;

import finki.ukim.mk.backendproject.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
	private String id;
	private LocationDto location;
	private String location_name;
	private String photo_reference;
	private PlaceType type;
}
