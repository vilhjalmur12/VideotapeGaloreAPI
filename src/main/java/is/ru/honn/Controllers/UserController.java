package is.ru.honn.Controllers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getUsers() {
        return userRepository.findAll();
    }

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
    public @ResponseBody String updateUser(@PathVariable("user_id") int user_id){
        //User currentUser = userRepository.findById(user_id);
        return "updated";
    }

    @RequestMapping(value= "/user", method = RequestMethod.POST)
    public @ResponseBody String createUser() {

        User user = new User();

        user.setFirstName("villi");
        user.setLastName("villason");
        user.setAddress("reynihlid");
        user.setEmail("villi@villi.is");
        user.setPhone("7752158");
        userRepository.save(user);
        return "Saved";
    }
}
