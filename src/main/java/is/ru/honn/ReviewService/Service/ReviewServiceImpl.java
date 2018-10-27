package is.ru.honn.ReviewService.Service;


import is.ru.honn.DTO.ReviewDTO;
import is.ru.honn.ReviewService.Domain.ReviewRepository;
import is.ru.honn.Entities.Review;
import is.ru.honn.InputModels.ReviewInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The main service implementaion in UserService component. Holds all business logic to work with correct objects.
 *
 * @version 1.0, 26 Okt 2018
 */
@Component(value = "ReviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * gets all reviews
     *
     * @return a list of ReviewDTO object models
     */
    public List<ReviewDTO> getAllReviews() {
        Iterable<Review> allReviews = reviewRepository.findAll();
        List<ReviewDTO> retReviews = new ArrayList<>();

        for (Review rev : allReviews) {
            retReviews.add(reviewToReviewDTO(rev));
        }

        return retReviews;
    }

    /**
     * gets all reviews user has made on any videotape
     *
     * @param id the users id
     * @return a list of ReviewDTO object model
     */
    public List<ReviewDTO> getAllReviewsByUserId(Integer id) {
        List<Review> allReviews = reviewRepository.getAllReviewsByUser(id);
        List<ReviewDTO> retReview = new ArrayList<>();

        for (Review rev : allReviews) {
            retReview.add(reviewToReviewDTO(rev));
        }

        return retReview;
    }

    /**
     * gets all reviews made on a single videotape
     *
     * @param id the videotape id
     * @return
     */
    public List<ReviewDTO> getAllReviewsByTape(Integer id) {
        List<Review> allReviews = reviewRepository.getAllReviewsByTape(id);
        List<ReviewDTO> retReview = new ArrayList<>();

        for (Review rev : allReviews) {
            retReview.add(reviewToReviewDTO(rev));
        }

        return retReview;
    }

    /**
     * gets a review on tape by user
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model
     */
    public ReviewDTO getTapeReviewByUser(Integer user_id, Integer tape_id) {
        Review review = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(review == null) {
            return null;
        }

        return reviewToReviewDTO(review);
    }

    /**
     * creates a new review on videotape made by user
     *
     * @param input ReviewInput model object
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model of the newly created Review
     */
    public ReviewDTO createReview(ReviewInput input, Integer user_id, Integer tape_id) {
        if(reviewRepository.getReviewByUserAndTape(user_id, tape_id) != null) {
            return updateReview(input, user_id, tape_id);
        }

        Review review = new Review(tape_id, user_id, input.getRating());
        reviewRepository.save(review);

        return reviewToReviewDTO(review);
    }

    /**
     * updates a review made by user on videotape
     *
     * @param input ReviewInput object model to be updated
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model
     */
    public ReviewDTO updateReview(ReviewInput input, Integer user_id, Integer tape_id) {
        Review tmpReview = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(tmpReview == null) {
            return createReview(input, user_id, tape_id);
        }

        tmpReview.setRating(input.getRating());
        reviewRepository.save(tmpReview);
        return reviewToReviewDTO(tmpReview);
    }

    /**
     * deletes a review
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     */
    public void deleteReview(Integer user_id, Integer tape_id) {
        Review review = reviewRepository.getReviewByUserAndTape(user_id, tape_id);

        if(review != null) {
            reviewRepository.delete(review);
        }
    }


    /**
     * transforms a Review to ReviewDTO object model
     *
     * @param review the Review model
     * @return a new ReviewDTO object model
     */
    private ReviewDTO reviewToReviewDTO(Review review) {
        return new ReviewDTO(review.getTapeId(), review.getUserId(), review.getRating());
    }

}
