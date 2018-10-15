package is.ru.honn.Controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TapeController {

    @RequestMapping(value = "/tape", method = RequestMethod.GET)
    public String getTapes() {
        return "This is the first tape";
    }
}
