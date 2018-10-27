package is.ru.honn.RecommendationService.Controller;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.RecommendationService.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationController {

    @Autowired
    @Qualifier("RecommendationServiceImpl")
    private RecommendationService recommendationService;

    @RequestMapping(value = "/users/{user_id}/recommendation", method = RequestMethod.GET)
    public @ResponseBody
    List<Videotape> getRecommendations(@PathVariable Integer user_id) {
        return recommendationService.getRecommendations(user_id);
    }
}
