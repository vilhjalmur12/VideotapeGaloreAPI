package is.ru.honn.UserService.Service;

import is.ru.honn.UserService.Exceptions.JSONFileNotFoundException;
import org.json.simple.JSONArray;

public interface ReaderService {
    JSONArray getJsonArray();
    void readFromFile(String filepath) throws JSONFileNotFoundException;
    void writeToFile();
}
