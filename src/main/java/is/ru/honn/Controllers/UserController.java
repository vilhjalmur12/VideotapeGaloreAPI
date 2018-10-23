package is.ru.honn.Controllers;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Domain.UserRepository.UserRepositoryImpl;
import is.ru.honn.Entities.User;
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

    public @ResponseBody
    Iterable<User> getUsers() {
        return _userService.getAllUsers();
    }

    /*
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@PathVariable("user_id") int user_id){
        Optional toDelete = userRepository.findById(user_id);
        if(toDelete == null){
            return "User not found with id : " + user_id;
        }
        userRepository.deleteById(user_id);
        return "deleted";
    }
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.PUT)
    public @ResponseBody String updateUser(@PathVariable("user_id") int user_id, @RequestBody User user){
        Optional<User> currentUser = userRepository.findById(user_id);

        if(currentUser == null){
            return "user with id: " + user_id + " not found";
        }else{
            currentUser.get().setFirstName(user.getFirstName());
            currentUser.get().setLastName(user.getLastName());
            currentUser.get().setAddress(user.getAddress());
            currentUser.get().setEmail(user.getEmail());
            currentUser.get().setPhone(user.getPhone());
            userRepository.save(currentUser.get());
            return "updated";
        }

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
    */
}
