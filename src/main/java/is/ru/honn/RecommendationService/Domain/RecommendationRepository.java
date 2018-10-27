package is.ru.honn.RecommendationService.Domain;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface RecommendationRepository extends CrudRepository<UserTapeRelation, Integer> {

    @Query("SELECT h FROM UserTapeRelation h WHERE h.userId = :user_id")
    List<UserTapeRelation> getUserHistory(@Param("user_id") Integer user_id);

    @Query("select rel.tapeId " +
            "from UserTapeRelation rel " +
            "where not rel.userId = :user_id " +
            "group by rel.tapeId " +
            "order by count(rel.tapeId) desc ")
    ArrayList<Object> getAllRentalsExceptUsers(@Param("user_id") Integer user_id);

    @Query("SELECT item FROM Videotape item WHERE item.id = :tape_id")
    Videotape getTapeById(@Param("tape_id") Integer tape_id);

}
