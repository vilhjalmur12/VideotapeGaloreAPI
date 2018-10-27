package is.ru.honn.Controllers;

import is.ru.honn.UserService.Domain.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserControllerTest {
    private TestEntityManager entityManager;

    private UserRepository userRepository;

    // write test cases here
}
