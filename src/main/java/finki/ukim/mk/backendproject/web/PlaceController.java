package finki.ukim.mk.backendproject.web;

//import finki.ukim.mk.backendproject.services.interfaces.GameAttemptService;
import finki.ukim.mk.backendproject.enums.PlaceType;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.services.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/places/game")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService gameAttemptService) {
        this.placeService = gameAttemptService;
    }

    @GetMapping("/all")
    public List<Place> findAll(){
        return this.placeService.findAll();
    }

    @GetMapping("/{type}")
   public List<Place> placesByType (@PathVariable PlaceType type){
       return placeService.findAllByType(type);
    }

    @PostMapping
    public Place createPlace(@RequestBody Place place) {return placeService.createPlace(place);
    }
}
