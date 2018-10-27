package is.ru.honn.Services.UserService;

import is.ru.honn.DTO.UserDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.spi.CalendarNameProvider;

import is.ru.honn.DTO.UserDetailDTO;
import is.ru.honn.Domain.ReaderService.JSONReaderService;
import is.ru.honn.Domain.ReaderService.ReaderService;
import is.ru.honn.Domain.RelationRepository.RelationRepository;
import is.ru.honn.Domain.UserRepository.UserRepository;
import is.ru.honn.Entities.User;
import is.ru.honn.Entities.UserTapeRelation;
import is.ru.honn.Entities.Videotape;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUserDateReport(String loanDate) {
        List<UserTapeRelation> relations = userRepository.getUsersRentingByDate(java.sql.Date.valueOf(loanDate));

        if(relations.isEmpty()) {
            return null;
        }

        List<User> retList = new ArrayList<>();

        for(UserTapeRelation rel : relations) {
            Optional<User> tmp = userRepository.findById(rel.getUserId());

            if(tmp == null) {
                continue;
            }

            retList.add(tmp.get());
        }


        return retList;
    }
/*
    public List<User> getUserDateReportDuration(String loanDate, Integer loanDuration) {
        List<UserTapeRelation> allRelations = userRepository.getAllRelations();
        List<User> retList = new ArrayList<>();
        Date setDate = java.sql.Date.valueOf(loanDate);
        Calendar c = Calendar.getInstance();
        c.setTime(setDate);
        c.add(Calendar.DATE, loanDuration);
        Date newDate = new Date(c.getTimeInMillis());

        for (UserTapeRelation rel : allRelations) {
            if(rel.getReturnDate() != null) {
                continue;
            }

            if(setDate.compareTo())
        }


        return null;
    }

*/
    public List<User> getUserReportDuration(Integer loanDuration) {
        List<UserTapeRelation> allRelations = userRepository.getAllRelations();
        List<User> retList = new ArrayList<>();
        Date today = new Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, -loanDuration);
        Date oldDate = new Date(c.getTimeInMillis());

        for (UserTapeRelation rel : allRelations) {
            if(rel.getReturnDate() != null) {
                continue;
            }

            if(oldDate.compareTo(rel.getBorrowDate()) >= 0) {
                Optional<User> optUser = userRepository.findById(rel.getUserId());
                if(optUser.isPresent()) {
                    retList.add(optUser.get());
                } else {
                    continue;
                }
            }
        }

        return retList;
    }

    public UserDetailDTO getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser == null) {
            return null;
        }

        User user = optionalUser.get();

        UserDetailDTO transUser = new UserDetailDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone(),
                getAllTapesByUser(user.getId()),
                getAllTapesByUserOnLoan(user.getId())
        );

        return transUser;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public UserDTO updateUser(User user, Integer id) {
        Optional<User> tmpUser = userRepository.findById(user.getId())
                .map( foundUser -> {
                    foundUser.setFirstName(user.getFirstName());
                    foundUser.setLastName(user.getLastName());
                    foundUser.setAddress(user.getAddress());
                    foundUser.setPhone(user.getPhone());
                    foundUser.setEmail(user.getEmail());
                    return userRepository.save(foundUser);
                });

        return userToUserDTO(tmpUser.get());
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDTO userToUserDTO(User user) {
        return new UserDTO(user.getFirstName(),
                user.getLastName(), user.getAddress(), user.getEmail(), user.getPhone());
    }

    private UserDetailDTO userToUserDetailDTO(User user, List<Videotape> history, List<Videotape> onLoan) {
        return new UserDetailDTO(user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getEmail(), user.getPhone(), history, onLoan);
    }

    private List<Videotape> getAllTapesByUser(Integer id) {
        List<UserTapeRelation> relations = userRepository.getUserRelationsByUserId(id);

        List<Videotape> userTapes = new ArrayList<>();

        for (UserTapeRelation rel : relations) {
                userTapes.add(userRepository.getVideoTapeById(rel.getTapeId()));
        }

        return userTapes;
    }

    public List<Videotape> getAllTapesByUserOnLoan(Integer id) {
        List<UserTapeRelation> relations = userRepository.getUserRelationsByUserId(id);

        List<Videotape> userTapes = new ArrayList<>();

        for (UserTapeRelation rel : relations) {
            if(rel.getReturnDate() == null) {
                Videotape tape = userRepository.getVideoTapeById(rel.getTapeId());
                userTapes.add(tape);
            }
        }

        return userTapes;
    }


    //TODO: REMOVE TEST
    public List<UserTapeRelation> getAllRelations() {
        return userRepository.getAllRelations();
    }

    public List<Videotape> getAllTapes() {
        return userRepository.getAllTapes();
    }

}
