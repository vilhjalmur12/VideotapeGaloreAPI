package is.ru.honn.Domain.ReviewRepository;

import is.ru.honn.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT t FROM Review t WHERE t.tapeId = :id")
    List<Review> findReviewByTapeId(@Param("id") Integer id);


    @Query("SELECT r FROM Review r WHERE r.userId = :user_id AND r.tapeId = :tape_id")
    Review getSingleReview(@Param("tape_id") Integer tape_id, @Param("user_id") Integer user_id);




}
