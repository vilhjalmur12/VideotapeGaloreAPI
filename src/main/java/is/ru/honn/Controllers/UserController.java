package is.ru.honn.Controllers;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.DTO.UserDTO;
import is.ru.honn.DTO.UserDetailDTO;
import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Domain.UserRepository.UserRepositoryImpl;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService _userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getUsers() {
        return _userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    UserDetailDTO getUserById(@PathVariable Integer id) {
        return _userService.getUserById(id);
    }

    @RequestMapping(value= "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        _userService.createUser(user);
        return "Saved";
    }

    @RequestMapping(value= "/user/{id}", method = RequestMethod.PUT)
    public @ResponseBody UserDTO createUser(@RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        return _userService.updateUser(user, id);
    }

    @RequestMapping(value= "/user/{id}", method = RequestMethod.DELETE)
    public void createUser(@PathVariable Integer id) {
        _userService.deleteUserById(id);
    }

    @RequestMapping(value= "/user/{id}/tapes", method = RequestMethod.GET)
    public List<Videotape> getUserTapes(@PathVariable Integer id) {
        return _userService.getAllTapesByUserOnLoan(id);
    }


    // TODO: REMOVE
    @RequestMapping(value= "/relations", method = RequestMethod.GET)
    public List<UserTapeRelation> getUserTapes() {
        return _userService.getAllRelations();
    }

    @RequestMapping(value= "/tapes", method = RequestMethod.GET)
    public List<Videotape> getAllTapes() {
        return _userService.getAllTapes();
    }
}
