package is.ru.honn.Domain.VideotapeRepository;

import is.ru.honn.Entities.Videotape;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideotapeRepository extends CrudRepository<Videotape, Integer> {
    @Query("SELECT t FROM Videotape t WHERE t.id = :id")
    Videotape getVideoTapeById(@Param("id") Integer id);
}
