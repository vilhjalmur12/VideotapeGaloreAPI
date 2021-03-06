package is.ru.honn.VideotapeService.Service;

import is.ru.honn.DTO.VideoTapeDTO;
import is.ru.honn.Entities.Review;
import is.ru.honn.Entities.Videotape;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VideotapeService {
    Iterable<Videotape> getAllTapes();

    void createTape(Videotape tape);
    VideoTapeDTO getTapeById(Integer id);
    void deleteTape(Integer id);
    void updateTape(Integer id, Videotape tape);
    List<Videotape> getTapeDateReport(String loanDate);

}
