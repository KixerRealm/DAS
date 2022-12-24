package finki.ukim.mk.backendproject.services.impl;

import finki.ukim.mk.backendproject.dtos.PlaceDto;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.mappers.PlaceMapper;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.repository.PlaceRepository;
import finki.ukim.mk.backendproject.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

	private final PlaceRepository placeRepository;

	private Random random = ThreadLocalRandom.current();

	@Override
	public List<PlaceDto> findAll() {
		return placeRepository.findAll().stream()
				.map(PlaceMapper.INSTANCE::toDto)
				.toList();
	}

	@Override
	public List<PlaceDto> findAllByType(PlaceType type) {
		return type == PlaceType.ALL ? findAll() : placeRepository.findAllByType(type)
				.stream().map(PlaceMapper.INSTANCE::toDto)
				.toList();
	}

	@Override
	public PlaceDto findRandomPlaceByType(PlaceType placeType) {
		List<PlaceDto> all = findAllByType(placeType);
		return all.size() > 0 ? all.get(random.nextInt(all.size())) : null;
	}
}
