package is.ru.honn.Services;

import is.ru.honn.Domain.VideotapeRepository.VideotapeRepository;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.Services.VideotapeService.VideotapeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class VideotapeServiceTest {
    @Autowired
    private VideotapeService videotapeService;

    @MockBean
    private VideotapeRepository videotapeRepository;

    private static Date date = new Date(2015, 03, 02);
    private static Videotape tape = new Videotape(2000, "movie", "dir", "ector", "VHS", date, "222-1234-333");

    @Before
    public void setUp_findAll(){
        Iterable<Videotape> tmpTapes = new ArrayList<>();
        ((ArrayList<Videotape>) tmpTapes).add(tape);
        Date d = new Date(2015, 05, 21);
        ((ArrayList<Videotape>)tmpTapes).add(new Videotape(2000, "movie", "dir", "ector", "VHS", d, "222-1234-333"));
        Mockito.when(videotapeRepository.findAll()).thenReturn(tmpTapes);


        Mockito.doReturn(tape).when(videotapeRepository).findById(2000);
    }
    @Test
    public void getAllTapes_test(){
        Iterable<Videotape> tmpTapes = new ArrayList<>();
        ((ArrayList<Videotape>) tmpTapes).add(tape);
        Date d = new Date(2015, 05, 21);
        ((ArrayList<Videotape>)tmpTapes).add(new Videotape(2000, "movie", "dir", "ector", "VHS", d, "222-1234-333"));

        Iterable<Videotape> testlist = videotapeService.getAllTapes();
        assertThat(testlist)
                .isEqualTo(tmpTapes);


    }
    @Test
    public void getTapeById_Test(){
        Videotape tmpTape = tape;

        Videotape testTape = videotapeService.getTapeById(2000);
        assertThat(testTape).isEqualTo(tmpTape);

    }
    @Test
    public void createTape_Test(){
        videotapeService.createTape(tape);

        assertThat(videotapeService.getTapeById(2000)).isEqualTo(tape);
    }
    @Test
    public void deleteTape_Test(){
        videotapeService.deleteTape(2000);

        assertThat(videotapeService.getTapeById(2000)).isEqualTo(null);
    }
    @Test
    public void updateTape_Test(){
        Videotape tmpTape = new Videotape(2000, "moviee", "dirr", "ectorr", "VHS", date, "222-1234-3333" );
        videotapeService.updateTape(2000, tmpTape);
        assertThat(videotapeService.getTapeById(2000)).isEqualTo(tmpTape);
    }

}
