package is.ru.honn.Domain;

import is.ru.honn.Domain.ReaderService.JSONReaderService;
import is.ru.honn.Domain.ReaderService.ReaderService;
import org.json.simple.JSONArray;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataInitializer {
    ReaderService friendReader;
    ReaderService tapeReader;
    Connection connection;

    public DataInitializer() {
        friendReader = new JSONReaderService("./src/main/resources/Friends.json");
        tapeReader = new JSONReaderService("./src/main/resources/Videotapes.json");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VideotapeGalore?user=galore&password=galore_16");
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION: " + ex.getMessage());
        }
    }

    public void init() {
        initFriends(friendReader.getJsonArray());
        initTapes(tapeReader.getJsonArray());
    }

    private void initFriends(JSONArray friends) {

    }

    private void initTapes(JSONArray tapes) {

    }
}
