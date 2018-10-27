package is.ru.honn.Domain.UserRepository;

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

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM UserTapeRelation u WHERE u.userId = :id")
    List<UserTapeRelation> getUserRelationsByUserId(@Param("id") Integer id);

    @Query("SELECT t FROM Videotape t WHERE t.id = :id")
    Videotape getVideoTapeById(@Param("id") Integer id);

    @Query("SELECT i FROM UserTapeRelation i WHERE i.borrowDate = :date")
    List<UserTapeRelation> getUsersRentingByDate(@Param("date") Date date);

    // TODO: TEST REMOVE
    @Query("SELECT t FROM UserTapeRelation t")
    List<UserTapeRelation> getAllRelations();

    @Query("SELECT t FROM Videotape t")
    List<Videotape> getAllTapes();


}
