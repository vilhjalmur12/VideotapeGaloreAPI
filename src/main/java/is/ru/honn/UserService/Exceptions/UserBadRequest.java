package is.ru.honn.UserService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Bad Request")
public class UserBadRequest extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public UserBadRequest() {
        super("UserBadRequest: bad value input");
    }
}
