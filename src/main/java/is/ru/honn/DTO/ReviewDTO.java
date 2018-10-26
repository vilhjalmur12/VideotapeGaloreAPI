package is.ru.honn.DTO;

import is.ru.honn.Entities.Videotape;

public class ReviewDTO {
    private Integer tapeId;

    private Integer userId;

    private Integer rating;

    public ReviewDTO() {}

    public ReviewDTO(Integer tapeId, Integer userId,
                  Integer rating) {
        this.tapeId = tapeId;
        this.userId = userId;
        this.rating = rating;
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
}
