package is.ru.honn.RecommendationService.Service;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.RecommendationService.Domain.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The main service class for RecommendationService with all implementations
 *
 * @version 1.0, 26 Okt 2018
 */
@Component(value = "RecommendationServiceImpl")
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationRepository recommendationRepository;

    /**
     * gets all videotape recommendations for a user
     *
     * @param id users id
     * @return a list of Videotape entity models
     */
    public List<Videotape> getRecommendations(Integer id) {

        List<Object> relations = recommendationRepository.getAllRentalsExceptUsers(id);

        List<Videotape> tapes = new ArrayList<>();

        int index = 0;
        for(Object rel : relations) {
            Integer tmp = (Integer) rel;

            if(index > 10 || index >= relations.size()) {
                break;
            }

            tapes.add(recommendationRepository.getTapeById(tmp));
        }

        return tapes;

    }
}
