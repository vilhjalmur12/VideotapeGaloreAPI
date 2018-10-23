package is.ru.honn.Services.VideotapeService;

import is.ru.honn.Domain.ReaderService.JSONReaderService;
import is.ru.honn.Domain.ReaderService.ReaderService;
import is.ru.honn.Domain.VideotapeRepository.VideotapeRepository;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component(value = "VideotapeServiceImpl")
public class VideotapeServiceImpl implements VideotapeService {

    private VideotapeRepository videotapeRepository;

    @Autowired
    public VideotapeServiceImpl(VideotapeRepository videotapeRepository) {
        this.videotapeRepository = videotapeRepository;

        if(videotapeRepository.count() == 0) {
            init();
        }
    }

    private void init() {

        ReaderService reader = new JSONReaderService("./src/main/resources/Videotapes.json");
        JSONArray tapeList = reader.getJsonArray();

        for(Object jsonTape : tapeList) {
            JSONObject tmpTape = (JSONObject) jsonTape;


            if(!videotapeRepository.existsById(Integer.parseInt(tmpTape.get("id").toString()))) {
                Videotape newTape = new Videotape(Integer.parseInt(tmpTape.get("id").toString()),
                        tmpTape.get("title").toString(),
                        tmpTape.get("director_first_name").toString(),
                        tmpTape.get("director_last_name").toString(),
                        tmpTape.get("type").toString(),
                        java.sql.Date.valueOf(tmpTape.get("release_date").toString()),
                        tmpTape.get("eidr").toString()
                );

                videotapeRepository.save(newTape);
            }
        }
    }
}
