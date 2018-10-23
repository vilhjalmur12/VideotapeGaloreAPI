package is.ru.honn.Domain.RelationRepository;

import is.ru.honn.Entities.UserTapeRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends CrudRepository<UserTapeRelation, Integer> {
}
