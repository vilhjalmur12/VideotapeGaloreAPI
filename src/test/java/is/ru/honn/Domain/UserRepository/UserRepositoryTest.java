package is.ru.honn.Domain.UserRepository;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
import is.ru.honn.UserService.Domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private static User testUser = new User(2000 , "Villi", "villason",
                                                                "reynihlid", "email", "phone");

    private static Videotape testTape = new Videotape(2000, "TestTape", "Director First", "Director Last",
            "Tape type", java.sql.Date.valueOf("11-12-13"), "jhlkjh-345254-kljlkj");

    private static UserTapeRelation testRelation = new UserTapeRelation(3000, 2000, 2000,
            java.sql.Date.valueOf("01-05-18"), null);



    @Test
    public void getUserRelationsByUserId_Test(Integer id) {
        // given
        entityManager.persist(testRelation);
        entityManager.flush();
        List<UserTapeRelation> tapleList = new ArrayList<>();
        tapleList.add(testRelation);

        // when
        List<UserTapeRelation> repoValue = userRepository.getUserRelationsByUserId(2000);

        // then
        assertThat(repoValue).isEqualTo(tapleList);
    }

    @Test
    public void getVideoTapeById_Test(Integer id) {
        // given
        entityManager.persist(testTape);
        entityManager.flush();

        // when
        Videotape tmpTape = userRepository.getVideoTapeById(2000);

        // then
        assertThat(tmpTape).isEqualTo(testTape);

    }


}
