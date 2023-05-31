public class Player {
    private String name;

    private int rating;

    private int count;

    private final int DEFAULT_RATING = 1200;

    public Player (String name) {
        this.name = name;
        this.rating = DEFAULT_RATING;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
