package is.ru.honn.Services.VideotapeService;

import is.ru.honn.Domain.ReaderService.JSONReaderService;
import is.ru.honn.Domain.ReaderService.ReaderService;
import is.ru.honn.Domain.ReviewRepository.ReviewRepository;
import is.ru.honn.Domain.VideotapeRepository.VideotapeRepository;
import is.ru.honn.Entities.Review;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(value = "VideotapeServiceImpl")
public class VideotapeServiceImpl implements VideotapeService {

    private VideotapeRepository videotapeRepository;
    private ReviewRepository reviewRepository;


    @Autowired
    public VideotapeServiceImpl(VideotapeRepository videotapeRepository) {
        this.videotapeRepository = videotapeRepository;

        if (videotapeRepository.count() == 0) {
            init();
        }
    }

    private void init() {

        ReaderService reader = new JSONReaderService("./src/main/resources/Videotapes.json");
        JSONArray tapeList = reader.getJsonArray();

        for (Object jsonTape : tapeList) {
            JSONObject tmpTape = (JSONObject) jsonTape;


            if (!videotapeRepository.existsById(Integer.parseInt(tmpTape.get("id").toString()))) {
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

    public Iterable<Videotape> getAllTapes() {
        return videotapeRepository.findAll();
    }

    public Optional<Videotape> getTapeById(Integer id) {
        return videotapeRepository.findById(id);
    }

    public void createTape(Videotape tape) {
        Videotape t = new Videotape();

        t.setDirector_first_name(tape.getDirector_first_name());
        t.setDirector_last_name(tape.getDirector_last_name());
        t.setType(tape.getType());
        t.setRelease_date(tape.getRelease_date());
        t.setEidr(tape.getEidr());
        videotapeRepository.save(tape);
        return;
    }

    public void deleteTape(Integer id) {
        Optional toDelete = videotapeRepository.findById(id);
        if (toDelete == null) {
            return;
        } else {
            videotapeRepository.deleteById(id);
            return;
        }
    }

    public void updateTape(Integer id, Videotape tape) {
        Optional<Videotape> toUpdate = videotapeRepository.findById(id);
        if (toUpdate == null) {
            return;
        } else {
            toUpdate.get().setDirector_first_name(tape.getDirector_first_name());
            toUpdate.get().setDirector_last_name(tape.getDirector_last_name());
            toUpdate.get().setRelease_date(tape.getRelease_date());
            toUpdate.get().setType(tape.getType());
            toUpdate.get().setEidr(tape.getEidr());
            videotapeRepository.save(toUpdate.get());
            return;
        }
    }


}
