package is.ru.honn.DTO;

import is.ru.honn.Entities.Videotape;

import java.util.List;

public class UserDetailDTO {
    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String phone;

    private List<Videotape> tapeHistory;
    private List<Videotape> tapesOnLoan;

    public UserDetailDTO() {}

    public UserDetailDTO(String firstName, String lastName,
                   String address, String email, String phone, List<Videotape> tapeHistory, List<Videotape> tapesOnLoan) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.tapeHistory = tapesOnLoan;
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
