package is.ru.honn.Services.UserService;

import is.ru.honn.DTO.UserDTO;
import is.ru.honn.DTO.UserDetailDTO;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUsers();
    UserDetailDTO getUserById(Integer id);
    void createUser(User user);
    UserDTO updateUser(User user, Integer id);
    void deleteUserById(Integer id);
    List<Videotape> getAllTapesByUserOnLoan(Integer id);

    List<UserTapeRelation> getAllRelations();
    List<Videotape> getAllTapes();
}
