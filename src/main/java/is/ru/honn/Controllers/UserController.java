package is.ru.honn.Controllers;

import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody String getUsers() {
        return "This is the first tape";
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
