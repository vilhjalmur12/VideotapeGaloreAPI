package is.ru.honn.Controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Domain.UserRepository.UserRepositoryImpl;
import is.ru.honn.Entities.User;
import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService _userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<User> getUsers() {

        return _userService.getAllUsers();
    }

    @RequestMapping(value= "/user", method = RequestMethod.POST)
    public @ResponseBody String createUser() {

        User user = new User();

        user.setFirstName("villi");
        user.setLastName("villason");
        user.setAddress("reynihlid");
        user.setEmail("villi@villi.is");
        user.setPhone("7752158");

        _userService.createUser(user);
        return "Saved";
    }


}
