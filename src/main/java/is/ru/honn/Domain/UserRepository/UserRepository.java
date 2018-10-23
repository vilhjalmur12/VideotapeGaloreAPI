package is.ru.honn.Domain.UserRepository;

import org.springframework.data.repository.CrudRepository;
import is.ru.honn.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
