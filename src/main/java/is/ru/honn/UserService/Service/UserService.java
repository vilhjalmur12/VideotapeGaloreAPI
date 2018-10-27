package is.ru.honn.UserService.Service;

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
    boolean deleteUserById(Integer id);
    List<Videotape> getAllTapesByUserOnLoan(Integer id);
    List<User> getUserDateReport(String loanDate);
    List<User> getUserReportDuration(Integer loanDuration);
}
