package is.ru.honn.Domain.UserRepository;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepositoryCustom extends JpaRepository<UserTapeRelation, Integer>  {

}
