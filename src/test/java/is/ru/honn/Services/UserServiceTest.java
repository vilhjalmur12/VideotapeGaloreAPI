package is.ru.honn.Services;

import is.ru.honn.DTO.UserDTO;
import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.Services.UserService.UserService;
import is.ru.honn.Services.UserService.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestConfig {

        @Autowired
        @Qualifier("UserServiceImpl")
        private UserService userService;

        @MockBean
        private UserRepository userRepository;

        private static User testUser = new User(2000 , "Villi", "villason",
                "reynihlid", "email", "phone");

        private static Videotape testTape = new Videotape(2000, "TestTape", "Director First", "Director Last",
                "Tape type", java.sql.Date.valueOf("11-12-13"), "jhlkjh-345254-kljlkj");

        private static UserTapeRelation testRelation = new UserTapeRelation(3000, 2000, 2000,
                java.sql.Date.valueOf("01-05-18"), null);


        @Before
        public void setUp_findAll() {
            List<User> tmpUserList = new ArrayList<>();
            tmpUserList.add(testUser);
            tmpUserList.add(new User(2001 , "garrí", "garríson",
                    "reynihlid", "email", "phone"));

            // Get all mock
            Mockito.when(userRepository.findAll())
                    .thenReturn(tmpUserList);

            // Get user by id mock
            Mockito.doReturn(testUser).when(userRepository).findById(2000);
        }


        @Test
        public void getAllUsers_Test() {
            // given
            List<User> dtoList = new ArrayList<>();
            dtoList.add(testUser);
            dtoList.add(new User(2001 , "garrí", "garríson",
                    "reynihlid", "email", "phone"));

            // when
            List<User> testList = userService.getAllUsers();

            // then
            assertThat(testList)
                    .isEqualTo(dtoList);
        }

        @Test
        public void getUserById_Test(Integer id) {
            // given


            // when

            // then


        }


        private UserDTO userToUserDTO(User user) {
            return new UserDTO(user.getFirstName(),
                    user.getLastName(), user.getAddress(), user.getEmail(), user.getPhone());
        }

    }




}
