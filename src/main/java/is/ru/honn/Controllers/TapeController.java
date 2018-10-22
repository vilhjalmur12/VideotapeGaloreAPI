package is.ru.honn.Controllers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Domain.TapeRepository.TapeRepository;
import is.ru.honn.Entities.Tape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TapeController {


    @Autowired
    private TapeRepository tapeRepository;

    @RequestMapping(value = "/tapes", method = RequestMethod.GET)
    public Iterable getTapes() {
        return tapeRepository.findAll();
    }

    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.GET)
    public Optional<Tape> getTapeById(@PathVariable("tape_id") int tape_id) {
        return tapeRepository.findById(tape_id);
    }
    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.DELETE)
    public String deleteTapeById(@PathVariable("tape_id") int tape_id) {
        Optional toDelete = tapeRepository.findById(tape_id);
        if(toDelete == null){
            return "tape with id : " + tape_id + " not found";
        }else{
            tapeRepository.deleteById(tape_id);
            return "deleted";
        }

    }
    @RequestMapping(value = "/tapes/{tape_id}", method = RequestMethod.PUT)
    public String updateTapeById(@PathVariable("tape_id") int tape_id, @RequestBody Tape tape) {
        Optional<Tape> toUpdate = tapeRepository.findById(tape_id);
        if(toUpdate == null){
            return "tape with id : " + tape_id + " not found";
        }else {
            toUpdate.get().setFirstName(tape.getFirstName());
            toUpdate.get().setLastName(tape.getLastName());
            toUpdate.get().setReleaseDate(tape.getReleaseDate());
            toUpdate.get().setType(tape.getType());
            toUpdate.get().setEidr(tape.getEidr());
            tapeRepository.save(toUpdate.get());
            return "updated";
        }
    }

    @RequestMapping(value= "/tapes", method = RequestMethod.POST)
    public @ResponseBody
    String createUser(@RequestBody Tape t) {

        Tape tape = new Tape();

        tape.setFirstName(t.getFirstName());
        tape.setLastName(t.getLastName());
        tape.setType(t.getType());
        tape.setReleaseDate(t.getReleaseDate());
        tape.setEidr(t.getEidr());
        tapeRepository.save(tape);
        return "Saved";
    }
}
