package is.ru.honn.Entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@SequenceGenerator(name = "userSequence", sequenceName = "user_seq",initialValue = 0)
    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String phone;

    @OneToMany
    private List<UserTapeRelation> videotapes;

    public User() {}

    public User(Integer id, String firstName, String lastName,
                String address, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserTapeRelation> getVideotapes() {
        return videotapes;
    }

    public void setVideotapes(List<UserTapeRelation> videotapes) {
        this.videotapes = videotapes;
    }

    public void addVideoTapeRelation(UserTapeRelation rel) {
        videotapes.add(rel);
        videotapes.remove(rel);
    }

    @Override
    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }
        if(!(o instanceof User)) {
            return false;
        }

        User tmp = (User) o;

        return tmp.getId() == this.id;
    }

}
