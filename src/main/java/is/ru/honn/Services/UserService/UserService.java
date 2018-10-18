package is.ru.honn.Services.UserService;

import is.ru.honn.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer id);
}
