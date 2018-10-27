package is.ru.honn.DTO;

import is.ru.honn.Entities.User;
import is.ru.honn.Entities.Videotape;

import java.sql.Date;
import java.util.List;

public class VideoTapeDTO {
    private String title;

    private String director_first_name;

    private String director_last_name;

    private String type;

    private Date release_date;

    private String eidr;

    private List<User> userHistory;
    private List<User> userOnLoan;

    public VideoTapeDTO() {}

    public VideoTapeDTO(String title, String director_first_name,
                        String director_last_name, String type, Date release_date, String eidr, List<User> usersOnLoan) {
        this.title = title;
        this.director_first_name = director_first_name;
        this.director_last_name = director_last_name;
        this.type = type;
        this.release_date = release_date;
        this.eidr = eidr;
        this.userHistory = usersOnLoan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector_first_name() {
        return director_first_name;
    }

    public void setDirector_first_name(String director_first_name) {
        this.director_first_name = director_first_name;
    }

    public String getDirector_last_name() {
        return director_last_name;
    }

    public void setDirector_last_name(String director_last_name) {
        this.director_last_name = director_last_name;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEidr() {
        return eidr;
    }

    public void setEidr(String eidr) {
        this.eidr = eidr;
    }
}
