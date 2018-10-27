package is.ru.honn.VideotapeService.Domain;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


/**
 * The main connection to the database, extends CrudRepository for
 * CRUD methods for Videotapes objects in the database.
 *
 *
 * @Author Máni Sigurðsson
 * @Version 1.0, 26 Okt 2018
 */


@Repository
public interface VideotapeRepository extends CrudRepository<Videotape, Integer> {

    /**
     * gets a single videotape in database by id.
     * Sends a query.
     *
     * @param id the videotape id
     * @return a Videotape entity object
     */

    @Query("SELECT t FROM Videotape t WHERE t.id = :id")
    Videotape getVideoTapeById(@Param("id") Integer id);

    /**
     * gets all relations to a user by videotape id.
     * sends in a SQL query.
     *
     * @param id the users id
     * @return a list of UserTapeRelation objects
     */

    @Query("SELECT u FROM UserTapeRelation u WHERE u.tapeId = :id")
    List<UserTapeRelation> geUserRelationsByTapeId(@Param("id") Integer id);

    /**
     * gets a single user in database by id.
     * Sends a query.
     *
     * @param id the user id
     * @return a user entity object
     */

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(@Param("id") Integer id);

    /**
     * gets all tapes renting from a specific borrowing date
     *
     * @param date the input borrowing date
     * @return a list of UserTapeRelation object
     */
    @Query("SELECT i FROM UserTapeRelation i WHERE i.borrowDate = :date")
    List<UserTapeRelation> getUsersRentingByDate(@Param("date") Date date);
}
