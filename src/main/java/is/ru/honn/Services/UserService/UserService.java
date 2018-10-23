package is.ru.honn.Services.UserService;

import is.ru.honn.DTO.UserDTO;
import is.ru.honn.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Iterable<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    void createUser(User user);
}
