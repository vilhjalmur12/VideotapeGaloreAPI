package is.ru.honn.Controllers;

import java.util.concurrent.atomic.AtomicLong;

import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.VideotapeService.VideotapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TapeController {

    @Autowired
    @Qualifier("VideotapeServiceImpl")
    private VideotapeService _videotapeService;

    @RequestMapping(value = "/tape", method = RequestMethod.GET)
    public String getTapes() {
        return "This is the first tape";
    }
}
