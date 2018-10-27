package is.ru.honn.UserService.Domain;

import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import is.ru.honn.Entities.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * The main connection to the database, extends JpaRepository for
 * CRUD methods for User objects in the database.
 *
 * Since the repository extends JpaRepository, no CRUD implementation needs to be
 * implemented for the User entity.
 *
 * @Author Vilhjálmur Rúnar Vilhjálmsson
 * @Version 1.0, 26 Okt 2018
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * gets all relations to a videotape by user id.
     * sends in a SQL query.
     *
     * @param id the users id
     * @return a list of UserTapeRelation objects
     */
    @Query("SELECT u FROM UserTapeRelation u WHERE u.userId = :id")
    List<UserTapeRelation> getUserRelationsByUserId(@Param("id") Integer id);

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
     * gets all users renting from a specific borrowing date
     *
     * @param date the input borrowing date
     * @return a list of UserTapeRelation object
     */
    @Query("SELECT i FROM UserTapeRelation i WHERE i.borrowDate = :date")
    List<UserTapeRelation> getUsersRentingByDate(@Param("date") Date date);


    /**
     * gets all rentals by all users
     *
     * @return a list of UserTapeRelation objects
     */
    @Query("SELECT i FROM UserTapeRelation i")
    List<UserTapeRelation> getAllRelations();
}
