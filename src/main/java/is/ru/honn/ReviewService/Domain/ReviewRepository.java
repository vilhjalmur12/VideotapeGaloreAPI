package is.ru.honn.ReviewService.Domain;

import is.ru.honn.Entities.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.userId = :id")
    List<Review> getAllReviewsByUser(@Param("id") Integer id);

    @Query("SELECT r FROM Review r WHERE r.tapeId = :id")
    List<Review> getAllReviewsByTape(@Param("id") Integer id);

    @Query("SELECT r FROM Review r WHERE r.userId = :user_id AND r.tapeId = :tape_id")
    Review getReviewByUserAndTape(@Param("user_id") Integer user_id, @Param("tape_id") Integer tape_id);
}
