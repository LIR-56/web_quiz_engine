package engine;

public class QuizNotFoundException extends RuntimeException {
    private final long id;

    public QuizNotFoundException(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
