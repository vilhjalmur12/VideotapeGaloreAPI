package is.ru.honn.Domain.UserRepository;

import org.springframework.data.repository.CrudRepository;
import is.ru.honn.Entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
