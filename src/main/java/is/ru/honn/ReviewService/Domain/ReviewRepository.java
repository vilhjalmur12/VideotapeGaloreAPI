package is.ru.honn.ReviewService.Domain;

import is.ru.honn.Entities.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The repository fetching all data for the ReviewService to the database.
 * CRUD methods for Review objects in the database.
 *
 * Since the repository extends CrudRepository, no CRUD implementation needs to be
 * implemented for the Review entity.
 *
 * @version 1.0, 26 Okt 2018
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    /**
     * gets all reviews by users id
     *
     * @param id the users id
     * @return a list of Review entity models
     */
    @Query("SELECT r FROM Review r WHERE r.userId = :id")
    List<Review> getAllReviewsByUser(@Param("id") Integer id);

    /**
     * gets all reviews made on tape
     *
     * @param id the videotapes id
     * @return a list of Review entity models
     */
    @Query("SELECT r FROM Review r WHERE r.tapeId = :id")
    List<Review> getAllReviewsByTape(@Param("id") Integer id);

    /**
     * gets users review on a single videotape
     *
     * @param user_id the users id
     * @param tape_id the videotapes id
     * @return a single Review entity model
     */
    @Query("SELECT r FROM Review r WHERE r.userId = :user_id AND r.tapeId = :tape_id")
    Review getReviewByUserAndTape(@Param("user_id") Integer user_id, @Param("tape_id") Integer tape_id);
}
