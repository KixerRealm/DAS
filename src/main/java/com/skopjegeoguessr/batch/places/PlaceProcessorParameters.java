package com.skopjegeoguessr.batch.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceProcessorParameters {
	private String query;
	private String nextPageToken;
}
