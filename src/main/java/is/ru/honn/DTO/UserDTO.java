package is.ru.honn.DTO;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.Videotape;

import java.util.List;

public class UserDTO {

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String phone;

    public UserDTO() {}

    public UserDTO(String firstName, String lastName,
                   String address, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
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


}
