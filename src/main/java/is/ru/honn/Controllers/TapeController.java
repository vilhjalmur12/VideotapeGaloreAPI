package is.ru.honn.Controllers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Entities.Videotape;
import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.VideotapeService.VideotapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import is.ru.honn.Entities.Tape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TapeController {


    @Autowired
    @Qualifier("VideotapeServiceImpl")
    private VideotapeService _videotapeService;

    @RequestMapping(value = "/tape", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Videotape> getTapes() {
        return _videotapeService.getAllTapes();
    }

    @RequestMapping(value = "/tape", method = RequestMethod.POST)
    public @ResponseBody
    void createTape(@RequestBody Videotape tape){
        _videotapeService.createTape(tape);
    }
    @RequestMapping(value = "/tape/{tape_id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Videotape>  getTapeById(@PathVariable("tape_id") int tape_id){
        return _videotapeService.getTapeById(tape_id);
    }
    @RequestMapping(value = "/tape/{tape_id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteTapeById(@PathVariable("tape_id") int tape_id){
        _videotapeService.deleteTape(tape_id);
    }
    @RequestMapping(value = "/tape/{tape_id}", method = RequestMethod.PUT)
    public @ResponseBody
    void updateTapeById(@PathVariable("tape_id") int tape_id, @RequestBody Videotape tape){
        _videotapeService.updateTape(tape_id, tape);
    }

}
