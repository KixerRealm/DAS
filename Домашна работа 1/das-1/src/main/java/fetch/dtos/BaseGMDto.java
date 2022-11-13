package fetch.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseGMDto {
	private String id;
	private Double lat;
	private Double lng;
	private String name;
	private List<String> types;
	private String photoReference;
}
