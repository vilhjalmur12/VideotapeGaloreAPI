package is.ru.honn.ReviewService.Service;


import is.ru.honn.DTO.ReviewDTO;
import is.ru.honn.ReviewService.Domain.ReviewRepository;
import is.ru.honn.Entities.Review;
import is.ru.honn.InputModels.ReviewInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value = "ReviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDTO> getAllReviews() {
        Iterable<Review> allReviews = reviewRepository.findAll();
        List<ReviewDTO> retReviews = new ArrayList<>();

        for (Review rev : allReviews) {
            retReviews.add(reviewToReviewDTO(rev));
        }

        return retReviews;
    }

    public List<ReviewDTO> getAllReviewsByUserId(Integer id) {
        List<Review> allReviews = reviewRepository.getAllReviewsByUser(id);
        List<ReviewDTO> retReview = new ArrayList<>();

        for (Review rev : allReviews) {
            retReview.add(reviewToReviewDTO(rev));
        }

        return retReview;
    }

    public List<ReviewDTO> getAllReviewsByTape(Integer id) {
        List<Review> allReviews = reviewRepository.getAllReviewsByTape(id);
        List<ReviewDTO> retReview = new ArrayList<>();

        for (Review rev : allReviews) {
            retReview.add(reviewToReviewDTO(rev));
        }

        return retReview;
    }

    public ReviewDTO getTapeReviewByUser(Integer user_id, Integer tape_id) {
        Review review = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(review == null) {
            return null;
        }

        return reviewToReviewDTO(review);
    }

    public ReviewDTO createReview(ReviewInput input, Integer user_id, Integer tape_id) {
        if(reviewRepository.getReviewByUserAndTape(user_id, tape_id) != null) {
            return updateReview(input, user_id, tape_id);
        }

        Review review = new Review(tape_id, user_id, input.getRating());
        reviewRepository.save(review);

        return reviewToReviewDTO(review);
    }

    public ReviewDTO updateReview(ReviewInput input, Integer user_id, Integer tape_id) {
        Review tmpReview = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(tmpReview == null) {
            return createReview(input, user_id, tape_id);
        }

        tmpReview.setRating(input.getRating());
        reviewRepository.save(tmpReview);
        return reviewToReviewDTO(tmpReview);
    }

    public void deleteReview(Integer user_id, Integer tape_id) {
        Review review = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(review != null) {
            reviewRepository.delete(review);
        }
    }



    private ReviewDTO reviewToReviewDTO(Review review) {
        return new ReviewDTO(review.getTapeId(), review.getUserId(), review.getRating());
    }

}
