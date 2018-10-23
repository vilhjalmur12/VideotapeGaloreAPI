package is.ru.honn.Services.UserService;

import is.ru.honn.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import is.ru.honn.Domain.ReaderService.JSONReaderService;
import is.ru.honn.Domain.ReaderService.ReaderService;
import is.ru.honn.Domain.RelationRepository.RelationRepository;
import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RelationRepository relationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RelationRepository relationRepository) {

        this.userRepository = userRepository;
        this.relationRepository = relationRepository;

        if(userRepository.count() == 0) {
            init();
        }
    }

    private void init() {

        ReaderService reader = new JSONReaderService("./src/main/resources/Friends.json");
        JSONArray userList = reader.getJsonArray();

        for(Object jsonUser : userList) {
            JSONObject tmpUser = (JSONObject) jsonUser;



            if(!userRepository.existsById(Integer.parseInt(tmpUser.get("id").toString()))) {
                User newUser = new User(Integer.parseInt(tmpUser.get("id").toString()),
                        tmpUser.get("first_name").toString(),
                        tmpUser.get("last_name").toString(),
                        tmpUser.get("address").toString(),
                        tmpUser.get("email").toString(),
                        tmpUser.get("phone").toString()
                );

                newUser.setVideotapes(new ArrayList<>());

                if(tmpUser.containsKey("tapes")) {

                    // make user tape relations
                    for(Object rel : (JSONArray) tmpUser.get("tapes")) {
                        JSONObject tmpRel = (JSONObject) rel;

                        Integer relUserId = Integer.parseInt(tmpUser.get("id").toString());
                        Integer relTapeId = Integer.parseInt(tmpRel.get("id").toString());

                        UserTapeRelation userTapeRel = new UserTapeRelation(relUserId, relTapeId,
                                java.sql.Date.valueOf(tmpRel.get("borrow_date").toString()));

                        if(tmpRel.get("return_date") != null) {
                            userTapeRel.setReturnDate(java.sql.Date.valueOf(tmpRel.get("return_date").toString()));
                        }

                        relationRepository.save(userTapeRel);

                        newUser.addVideoTapeRelation(userTapeRel);
                    }
                }
                userRepository.save(newUser);
            }
        }
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {

        return userRepository.findById(id);
    }

    public void createUser(User user) {
        // TODO: setja allt hér inn frá controller

        userRepository.save(user);

    }
}
