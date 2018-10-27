package is.ru.honn.ReviewService.Service;

import is.ru.honn.DTO.ReviewDTO;
import is.ru.honn.Entities.Review;
import is.ru.honn.InputModels.ReviewInput;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService{
    List<ReviewDTO> getAllReviewsByUserId(Integer id);
    List<ReviewDTO> getAllReviews();
    List<ReviewDTO> getAllReviewsByTape(Integer id);
    ReviewDTO createReview(ReviewInput input, Integer user_id, Integer tape_id);
    ReviewDTO updateReview(ReviewInput input, Integer user_id, Integer tape_id);
    void deleteReview(Integer user_id, Integer tape_id);
    ReviewDTO getTapeReviewByUser(Integer user_id, Integer tape_id);
}
