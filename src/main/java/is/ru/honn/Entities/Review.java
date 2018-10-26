package is.ru.honn.Entities;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer tapeId;

    private Integer userId;

    private Integer rating;

    private String review;

    public Review() {}

    public Review(Integer id, Integer tapeId, Integer userId,
                  Integer rating, String review) {
        this.id = id;
        this.tapeId = tapeId;
        this.userId = userId;
        this.rating = rating;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTapeId() {
        return tapeId;
    }

    public void setTapeId(Integer tapeId) {
        this.tapeId = tapeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }
        if(!(o instanceof Review)) {
            return false;
        }

        Review tmp = (Review) o;

        return tmp.getId() == this.id;
    }
}
