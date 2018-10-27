package is.ru.honn.UserService.Service;

import is.ru.honn.UserService.Exceptions.JSONFileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReaderService implements ReaderService {
    private JSONParser parser;
    private JSONArray list;

    public JSONReaderService(String filepath) throws JSONFileNotFoundException {
        parser = new JSONParser();
        readFromFile(filepath);
    }

    public JSONArray getJsonArray() {
        return list;
    }
    public void readFromFile(String filepath) throws JSONFileNotFoundException  {
        JSONArray jsonArray = null;

        try {
            Object obj = parser.parse(new FileReader(filepath));
            jsonArray = (JSONArray) obj;

        } catch (ParseException pex) {
            throw new JSONFileNotFoundException("Parsing file error");
        } catch (IOException ioex) {
            throw new JSONFileNotFoundException("IO Exception");
        }

        this.list = jsonArray;
    }

    public void writeToFile() {

    }
}
