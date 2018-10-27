package is.ru.honn.ReviewService.Controller;

import is.ru.honn.DTO.ReviewDTO;
import is.ru.honn.InputModels.ReviewInput;
import is.ru.honn.ReviewService.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * A front controller which works as a gateway to the service. Uses spring boot REST services.
 * Mappes path urls to functions.
 *
 * @Version 1.0, 26 Okt 2018
 */
@RestController
public class ReviewController {

    @Autowired
    @Qualifier("ReviewServiceImpl")
    private ReviewService reviewService;

    /**
     * GET
     * Gets all reviews made.
     *
     * @return ReviewDTO object model
     */
    @RequestMapping(value = "/tapes/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    /**
     * GET
     * Gets all reviews a user has made
     *
     * @param user_id the users id
     * @return a list of ReviewDTO object models
     */
    @RequestMapping(value = "/users/{user_id}/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllUserReviews(@PathVariable Integer user_id) {
        return reviewService.getAllReviewsByUserId(user_id);
    }

    /**
     * GET
     * Gets all reviews made on a single tape
     *
     * @param tape_id the Videotapes id
     * @return a list of ReviewDTO object models
     */
    @RequestMapping(value = "/tapes/{tape_id}/reviews", method = RequestMethod.GET)
    public @ResponseBody
    List<ReviewDTO> getAllReviewsOnTape(@PathVariable Integer tape_id) {
        return reviewService.getAllReviewsByTape(tape_id);
    }

    /**
     * GET
     * Gets a review user made on tape
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model
     */
    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.GET)
    public @ResponseBody ReviewDTO getTapeUserReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.getTapeReviewByUser(user_id, tape_id);
    }

    /**
     * POST
     * creates a new review from user on a videotape
     *
     * @param input a ReviewInput object model to be passed on
     * @param user_id the users id in path
     * @param tape_id the videotapes id in path
     * @return a ReviewDTO object model of the newly created review
     */
    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.POST)
    public @ResponseBody ReviewDTO createReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.createReview(input, user_id, tape_id);
    }

    /**
     * PUT
     * updates a review a user made on a Videotape
     *
     * @param input a ReviewInput object model to be updated
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model which got updated
     */
    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.PUT)
    public @ResponseBody ReviewDTO updateReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.updateReview(input, user_id, tape_id);
    }

    /**
     * PUT
     * updates a review a user made on a videotape
     * (note this has a differrent path than another updated function in this class)
     *
     * @param input a ReviewInput object model to be updated
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a ReviewDTO object model which got updated
     */
    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.PUT)
    public @ResponseBody ReviewDTO updateUsersReview(
            @RequestBody ReviewInput input,
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        return reviewService.updateReview(input, user_id, tape_id);
    }

    /**
     * DELETE
     * deletes a review user made on videotape
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     */
    @RequestMapping(value = "/users/{user_id}/reviews/{tape_id}", method = RequestMethod.DELETE)
    public void deleteReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        reviewService.deleteReview(user_id, tape_id);
    }

    /**
     * DELETE
     * deletes a review user made on videotape
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     */
    @RequestMapping(value = "/tapes/{tape_id}/reviews/{user_id}", method = RequestMethod.DELETE)
    public void deleteUsersReview(
            @PathVariable Integer user_id,
            @PathVariable Integer tape_id) {
        reviewService.deleteReview(user_id, tape_id);
    }


}
