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

/**
 * Extends the CrudRepository abstract class for UserTapeRelation. Therefore has all entity management
 * connections to the database.
 *
 * @version 1.0, 26 Okt 2018
 */
@Repository
public interface RecommendationRepository extends CrudRepository<UserTapeRelation, Integer> {

    /**
     * gets all data on users rental history to the database.
     *
     * @param user_id the users id
     * @return a list of UserTapeRelation entity model
     */
    @Query("SELECT h FROM UserTapeRelation h WHERE h.userId = :user_id")
    List<UserTapeRelation> getUserHistory(@Param("user_id") Integer user_id);

    /**
     * gets all rentals made by all users except the user requesting. returns the count
     * for every videotape how often it has been rented. The query sorts the most rented tapes
     * to the top so we can easily fetch the most popular videotapes.
     *
     * @param user_id the users id
     * @return an ArrayList of objects, in this case only the ID's of the tapes.
     */
    @Query("select rel.tapeId " +
            "from UserTapeRelation rel " +
            "where not rel.userId = :user_id " +
            "group by rel.tapeId " +
            "order by count(rel.tapeId) desc ")
    ArrayList<Object> getAllRentalsExceptUsers(@Param("user_id") Integer user_id);

    /**
     * gets a videotape by id from the database
     *
     * @param tape_id the videotape id
     * @return a single Videotape entity model
     */
    @Query("SELECT item FROM Videotape item WHERE item.id = :tape_id")
    Videotape getTapeById(@Param("tape_id") Integer tape_id);

}
