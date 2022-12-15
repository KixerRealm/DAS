package finki.ukim.mk.backendproject.web;

//import finki.ukim.mk.backendproject.services.interfaces.GameAttemptService;
import finki.ukim.mk.backendproject.models.Place;
import finki.ukim.mk.backendproject.services.interfaces.PlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/places/game/start")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService gameAttemptService) {
        this.placeService = gameAttemptService;
    }

    @GetMapping("/all")
    public List<Place> findAll(){
        return this.placeService.findAll();
    }

//    @GetMapping("/{id}")
//    public Optional<GameAttempt> gameAttempt(@PathVariable String id){
//        //return gameAttemptService.findById(id);
//    }
}
