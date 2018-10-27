package is.ru.honn.RecommendationService.Service;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {
    List<Videotape> getRecommendations(Integer id);
}
