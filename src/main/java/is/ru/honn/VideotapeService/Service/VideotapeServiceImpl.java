package is.ru.honn.VideotapeService.Service;

import is.ru.honn.DTO.VideoTapeDTO;
import is.ru.honn.ReviewService.Domain.ReviewRepository;
import is.ru.honn.UserService.Domain.UserRepository;
import is.ru.honn.VideotapeService.Domain.VideotapeRepository;

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
import java.util.Calendar;
import java.sql.Date;


/**
 * The main service implementation for the VideotapeService.
 *
 * @author Máni Sigurðsson
 * @version 1.0, 26 Oct 2018
 */
@Component(value = "VideotapeServiceImpl")
public class VideotapeServiceImpl implements VideotapeService {

    private VideotapeRepository videotapeRepository;

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;


    @Autowired
    public VideotapeServiceImpl(VideotapeRepository videotapeRepository) {
        this.videotapeRepository = videotapeRepository;

        if (videotapeRepository.count() == 0) {
            init();
        }
    }
    /**
     * JSON initializer for the User database if it is empty. This gets called in the constructor.
     */

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
    /**
     * getAllTapes reaches to the domain layer to find all videoTapes
     *
     * @return a Iterable<Videotap> of videotape entities
     */
    public Iterable<Videotape> getAllTapes() {
        return videotapeRepository.findAll();
    }
    /**
     * getTapeDateReport finds all videotapes that have been borrowed for a specific time.
     *
     * @param loanDate a date string to be valued
     * @return a list of VideoTape entities
     */

    public List<Videotape> getTapeDateReport(String loanDate) {
        List<UserTapeRelation> relations = videotapeRepository.getUsersRentingByDate(java.sql.Date.valueOf(loanDate));

        if(relations.isEmpty()) {
            return null;
        }

        List<Videotape> retList = new ArrayList<>();

        for(UserTapeRelation rel : relations) {
            Optional<Videotape> tmp = videotapeRepository.findById(rel.getTapeId());

            if(tmp == null) {
                continue;
            }

            retList.add(tmp.get());
        }


        return retList;
    }

    /**
     * gets a videotape by id
     *
     * @param id tape id as Integer
     * @return a single VideoTapeDTO object to be passed
     */
    public VideoTapeDTO getTapeById(Integer id) {
        Optional<Videotape> checkTape = videotapeRepository.findById(id);
        if(checkTape == null){
            return null;
        }
        Videotape tape = checkTape.get();

        VideoTapeDTO returnTape = new VideoTapeDTO(
                tape.getTitle(),
                tape.getDirector_first_name(),
                tape.getDirector_last_name(),
                tape.getType(),
                tape.getRelease_date(),
                tape.getEidr(),
                getAllUsersByTape(tape.getId())
        );
        return returnTape;
    }
    /**
     * creates a videotape and saves it
     *
     * @param tape object to be created
     */
    public void createTape(Videotape tape) {
        Videotape t = new Videotape();

        t.setDirector_first_name(tape.getDirector_first_name());
        t.setDirector_last_name(tape.getDirector_last_name());
        t.setType(tape.getType());
        t.setRelease_date(tape.getRelease_date());
        t.setEidr(tape.getEidr());
        videotapeRepository.save(tape);
    }

    /**
     * Deletes videoTape
     *
     * @param id the videotape id to be deleted
     */

    public void deleteTape(Integer id) {
        Optional toDelete = videotapeRepository.findById(id);
        if (toDelete == null) {
            return;
        } else {
            videotapeRepository.deleteById(id);
            return;
        }
    }

    /**
     * Updates a videoTape
     *
     * @param id the videoTape id to be update
     * @param tape object to update with
     */

    public void updateTape(Integer id, Videotape tape) {
        Optional<Videotape> tmpTape = videotapeRepository.findById(id)
                .map( foundTape -> {
                    foundTape.setTitle(tape.getTitle());
                    foundTape.setDirector_first_name(tape.getDirector_first_name());
                    foundTape.setDirector_last_name(tape.getDirector_last_name());
                    foundTape.setRelease_date(tape.getRelease_date());
                    foundTape.setType(tape.getType());
                    foundTape.setEidr(tape.getEidr());
                    return foundTape;
                });
        videotapeRepository.save(tmpTape.get());
    }

    /**
     * gets all Videotapes user has had (history)
     *
     * @param id the tape id
     * @return a list of UserTapeRelation objects
     */

    private List<User> getAllUsersByTape(Integer id) {
        List<UserTapeRelation> relations = videotapeRepository.geUserRelationsByTapeId(id);

        List<User> userTapes = new ArrayList<>();

        for (UserTapeRelation rel : relations) {
            userTapes.add(videotapeRepository.getUserById(rel.getUserId()));
        }

        return userTapes;
    }


}
