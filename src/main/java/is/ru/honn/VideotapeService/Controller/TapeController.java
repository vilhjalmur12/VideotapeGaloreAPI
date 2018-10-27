package is.ru.honn.VideotapeService.Controller;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.DTO.VideoTapeDTO;
import is.ru.honn.Entities.Review;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.VideotapeService.Service.VideotapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

/**
 * A controller for the VideoTapeService. Works as the gateway to the service below. Mapped for request parameters
 *
 * @author Máni Sigurðsson
 * @version 1.0, 26 Oct 2018
 */

@RestController
public class TapeController {


    @Autowired
    @Qualifier("VideotapeServiceImpl")
    private VideotapeService _videotapeService;
    /**
     * GET
     * Gateway to get all Tapes as Iterable<Videotape>.
     *
     * @Param LoanDate An optional parameter for a date in string
     *
     * @Return an Iterable<Videotap> of Videotapes entities to be passed through HTTP
     */
    @RequestMapping(value = "/tapes", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Videotape> getTapes(
            @RequestParam( value = "LoanDate", required = false) Optional<String> LoanDate
    ) {
        if (LoanDate.isPresent()) {
            String sDate = LoanDate.get();

            return _videotapeService.getTapeDateReport(sDate);

        }
        return _videotapeService.getAllTapes();

    }

    /**
     * POST
     * Gateway to create videotape
     *
     * @Param videotape the videotape object to be created
     */

    @RequestMapping(value = "/tapes", method = RequestMethod.POST)
    public @ResponseBody
    void createTape(@RequestBody Videotape tape){
        _videotapeService.createTape(tape);
    }

    /**
     * GET
     * Gateway to get a single Videotape.
     *
     * @Param id the users id to get in the path
     *
     * @Return a videotapeDTO entity to be passed through HTTP
     */

    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.GET)
    public @ResponseBody
    VideoTapeDTO getTapeById(@PathVariable("tape_id") int tape_id){
        return _videotapeService.getTapeById(tape_id);
    }

    /**
     * DELETE
     * Gateway to delete a Videotape
     *
     * @Param id the id of the Videotape to be deleted
     */

    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteTapeById(@PathVariable("tape_id") int tape_id){
        _videotapeService.deleteTape(tape_id);
    }

    /**
     * PUT
     * Gateway to update a videotape
     *
     * @Param id path parameter of the videotape to be updated
     * @Param videotape an object mapped with videotape entity to be updated
     *
     */

    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.PUT)
    public @ResponseBody
    void updateTapeById(@PathVariable("tape_id") int tape_id, @RequestBody Videotape tape){
        tape.setId(tape_id);
        _videotapeService.updateTape(tape_id, tape);
    }
}
