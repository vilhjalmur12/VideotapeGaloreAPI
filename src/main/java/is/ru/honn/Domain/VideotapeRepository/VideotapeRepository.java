package is.ru.honn.Domain.VideotapeRepository;

import is.ru.honn.Entities.Videotape;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideotapeRepository extends CrudRepository<Videotape, Integer> {
}
