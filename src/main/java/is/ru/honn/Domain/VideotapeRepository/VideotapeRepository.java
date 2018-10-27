package is.ru.honn.Domain.VideotapeRepository;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideotapeRepository extends CrudRepository<Videotape, Integer> {
    @Query("SELECT t FROM Videotape t WHERE t.id = :id")
    Videotape getVideoTapeById(@Param("id") Integer id);

    @Query("SELECT u FROM UserTapeRelation u WHERE u.tapeId = :id")
    List<UserTapeRelation> geUserRelationsByTapeId(@Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(@Param("id") Integer id);
}
