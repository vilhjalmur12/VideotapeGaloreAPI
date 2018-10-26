package is.ru.honn.InputModels;

public class ReviewInput {
    private Integer rating;

    public ReviewInput() {

    }

    public ReviewInput(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
