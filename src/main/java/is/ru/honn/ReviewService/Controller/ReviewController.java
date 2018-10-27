package is.ru.honn.ReviewService.Controller;

import is.ru.honn.DTO.ReviewDTO;
import is.ru.honn.InputModels.ReviewInput;
import is.ru.honn.ReviewService.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    @Qualifier("ReviewServiceImpl")
    private ReviewService reviewService;

    @RequestMapping(value = "/tapes/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @RequestMapping(value = "/users/{user_id}/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllUserReviews(@PathVariable Integer user_id) {
        return reviewService.getAllReviewsByUserId(user_id);
    }

    @RequestMapping(value = "/tapes/{tape_id}/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllReviewsOnTape(@PathVariable Integer tape_id) {
        return reviewService.getAllReviewsByTape(tape_id);
    }

    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.GET)
    public @ResponseBody ReviewDTO getTapeUserReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.getTapeReviewByUser(user_id, tape_id);
    }

    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.POST)
    public @ResponseBody ReviewDTO createReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.createReview(input, user_id, tape_id);
    }

    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.PUT)
    public @ResponseBody ReviewDTO updateReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.updateReview(input, user_id, tape_id);
    }

    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.PUT)
    public @ResponseBody ReviewDTO updateUsersReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.updateReview(input, user_id, tape_id);
    }

    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.DELETE)
    public void deleteReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        reviewService.deleteReview(user_id, tape_id);
    }

    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.DELETE)
    public void deleteUsersReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        reviewService.deleteReview(user_id, tape_id);
    }


}
