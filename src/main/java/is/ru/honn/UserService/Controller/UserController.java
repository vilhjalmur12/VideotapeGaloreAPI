package is.ru.honn.UserService.Controller;

import java.util.List;
import java.util.Optional;
import is.ru.honn.DTO.UserDTO;
import is.ru.honn.DTO.UserDetailDTO;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.UserService.Exceptions.UserBadRequest;
import is.ru.honn.UserService.Exceptions.UserNotFoundException;
import is.ru.honn.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


/**
 * A controller for the UserService. Works as the gateway to the service below. Mapped for request parameters
 *
 * @author Vilhjálmur Rúnar Vilhjálmsson
 * @version 1.0, 26 Oct 2018
 */
@RestController
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService _userService;

    /**
     * GET
     * Gateway to get all users as list.
     *
     * @Param LoanDate An optional parameter for a date in string
     * @Param LoanDuration An optional parameter for amount of days int
     *
     * @Return an ArrayList of User entities to be passed through HTTP
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getUsers(
            @RequestParam( value = "LoanDate", required = false) Optional<String> LoanDate,
            @RequestParam( value = "LoanDuration", required = false) Optional<Integer> LoanDuration) {

        if (LoanDate.isPresent()) {
            String sDate = LoanDate.get();
            if (LoanDuration.isPresent()) {

            } else {
                return _userService.getUserDateReport(sDate);
            }
        } else if (LoanDuration.isPresent()) {
            Integer iDuration = LoanDuration.get();
            return _userService.getUserReportDuration(iDuration);
        }

        return _userService.getAllUsers();
    }

    /**
     * GET
     * Gateway to get a single user.
     *
     * @Param id the users id to get in the path
     *
     * @Return a user entity to be passed through HTTP
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody
    UserDetailDTO getUserById(@PathVariable Integer id) throws UserNotFoundException {

        UserDetailDTO user = _userService.getUserById(id);
        if(user == null) {
            throw new UserNotFoundException(id);
        }
        return _userService.getUserById(id);
    }

    /**
     * POST
     * Gateway to create user
     *
     * @Param user the user object to be created
     *
     * @Return saved.
     */
    @RequestMapping(value= "/users", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) throws UserBadRequest {
        if(!userParamChecker(user)) {
            throw new UserBadRequest();
        }
        _userService.createUser(user);
        return "saved";
    }

    /**
     * PUT
     * Gateway to update a user
     *
     * @Param user an object mapped with User entity to be updated
     * @Param id path parameter of the user to be updated
     *
     * @Return a user updated
     */
    @RequestMapping(value= "/users/{id}", method = RequestMethod.PUT)
    public @ResponseBody UserDTO updateUser(@RequestBody User user, @PathVariable Integer id) throws UserBadRequest {
        if(!userParamChecker(user)) {
            throw new UserBadRequest();
        }
        user.setId(id);
        return _userService.updateUser(user, id);
    }

    /**
     * DELETE
     * Gateway to delete a user
     *
     * @Param id the id of the user to be deleted
     */
    @RequestMapping(value= "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer id) throws UserNotFoundException {

        if(!_userService.deleteUserById(id)) {
            throw new UserNotFoundException(id);
        }
    }

    /**
     * GET
     * Gateway to get all tapes that the user has had. The history.
     *
     * @Param id the users id to fetch the list
     *
     * @Return a list of Videotapes to be passed through HTTP
     */
    @RequestMapping(value= "/users/{id}/tapes", method = RequestMethod.GET)
    public List<Videotape> getUserTapes(@PathVariable Integer id) {
        return _userService.getAllTapesByUserOnLoan(id);
    }


    /**
     * Helper function to check incoming user parameters
     *
     * @param user the user object
     * @return true only if the user has all values
     */
    private boolean userParamChecker(User user) {
        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getAddress() == null || user.getEmail() == null
                || user.getPhone() == null) {
            return false;
        }
        return true;
    }
}
