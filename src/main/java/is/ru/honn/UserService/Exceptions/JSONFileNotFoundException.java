package is.ru.honn.UserService.Exceptions;

public class JSONFileNotFoundException extends Exception {
    public JSONFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
