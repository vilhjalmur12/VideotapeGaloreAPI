package is.ru.honn.RecommendationService.Controller;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.RecommendationService.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller acting as gateway to a RecommendationService.
 * Uses Spring RestController.
 *
 * @version 1.0, 26 Okt 2018
 */
@RestController
public class RecommendationController {

    @Autowired
    @Qualifier("RecommendationServiceImpl")
    private RecommendationService recommendationService;

    /**
     * gets a list of videotape recommendations for the user.
     *
     * @param user_id the users id
     * @return a list of Videotape entity models
     */
    @RequestMapping(value = "/users/{user_id}/recommendation", method = RequestMethod.GET)
    public @ResponseBody
    List<Videotape> getRecommendations(@PathVariable Integer user_id) {
        return recommendationService.getRecommendations(user_id);
    }
}
