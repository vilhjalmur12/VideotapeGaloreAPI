package is.ru.honn.Services;

import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.UserService.UserServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestConfig {
        /*
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
        */
    }




}
