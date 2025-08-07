package engine;

public class QuizNotFoundException extends RuntimeException {
    private final int id;

    public QuizNotFoundException(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
