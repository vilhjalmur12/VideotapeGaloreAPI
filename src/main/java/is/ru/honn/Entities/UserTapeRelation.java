package is.ru.honn.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class UserTapeRelation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer tapeId;

    private Date borrowDate;

    private Date returnDate;

    public UserTapeRelation() {

    }

    public UserTapeRelation(Integer id, Integer userId, Integer tapeId,
                            Date borrowDate, Date returnDate)
    {
        this.id = id;
        this.userId = userId;
        this.tapeId = tapeId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public UserTapeRelation(Integer userId, Integer tapeId,
                            Date borrowDate)
    {
        this.userId = userId;
        this.tapeId = tapeId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTapeId() {
        return tapeId;
    }

    public void setTapeId(Integer tapeId) {
        this.tapeId = tapeId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }
        if(!(o instanceof UserTapeRelation)) {
            return false;
        }

        UserTapeRelation tmp = (UserTapeRelation) o;

        return tmp.getId() == this.id;
    }
}
