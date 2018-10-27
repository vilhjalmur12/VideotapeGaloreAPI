package is.ru.honn.VideotapeService.Service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReaderService implements ReaderService {
    private JSONParser parser;
    private JSONArray list;

    public JSONReaderService(String filepath) {
        parser = new JSONParser();
        readFromFile(filepath);
    }

    public JSONArray getJsonArray() {
        return list;
    }
    public void readFromFile(String filepath) {
        JSONArray jsonArray = null;

        try {
            //
            Object obj = parser.parse(new FileReader(filepath));
            jsonArray = (JSONArray) obj;

        } catch (ParseException pex) {
            System.out.println("Parsing file error");
        } catch (IOException ioex) {
            System.out.println("IO Exception");
        }

        this.list = jsonArray;
    }

    public void writeToFile() {

    }
}
