package is.ru.honn.VideotapeService.Service;

import org.json.simple.JSONArray;

public interface ReaderService {
    JSONArray getJsonArray();
    void readFromFile(String filepath);
    void writeToFile();
}
