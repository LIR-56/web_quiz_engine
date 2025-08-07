package engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizService {
    private final ArrayList<Quiz> quizzes;

    public QuizService() {
        this.quizzes = new ArrayList<>();
    }

    public int addQuiz(Quiz quiz) {
        synchronized (quizzes) {
            quizzes.add(quiz);
            return quizzes.size() - 1;
        }
    }

    public Quiz getById(int id) {
        if (quizzes.size() > id && quizzes.get(id) != null) {
            return quizzes.get(id);
        }
        throw new QuizNotFoundException(id);
    }

    public List<Quiz> getAllQuizes() {
        ArrayList<Quiz> result;

        synchronized (quizzes) {
            result = (ArrayList<Quiz>) quizzes.clone();
        }
        return result;
    }

    public boolean checkAnswer(int quizId, int result) {
        return getById(quizId).getAnswer() == result;
    }
}
