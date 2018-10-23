package is.ru.honn.Domain.ReaderService;

import org.json.simple.JSONArray;

public interface ReaderService {
    JSONArray getJsonArray();
    void readFromFile(String filepath);
    void writeToFile();
}
