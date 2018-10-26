package is.ru.honn.Services.VideotapeService;

import is.ru.honn.Entities.Review;
import is.ru.honn.Entities.Videotape;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VideotapeService {
    Iterable<Videotape> getAllTapes();
    void createTape(Videotape tape);
    Optional<Videotape> getTapeById(Integer id);
    void deleteTape(Integer id);
    void updateTape(Integer id, Videotape tape);
    Iterable<Review> getAllReviews();
    List<Review> getReviewByTapeId(int id);

    Review getUserReview(int tape_id, int user_id);

    void updateUserReview(int tape_id, int user_id, Review r);

    void deleteUserReview(int tape_id, int user_id);

}
