package is.ru.honn.Domain.TapeRepository;

import is.ru.honn.Entities.Tape;
import org.springframework.data.repository.CrudRepository;

public interface TapeRepository extends CrudRepository<Tape, Integer> {
}
