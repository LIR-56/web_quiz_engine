package engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class QuizService {

    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;

    public QuizService(QuizMapper quizMapper, QuizRepository quizRepository) {
        this.quizMapper = quizMapper;
        this.quizRepository = quizRepository;
    }

    public long addQuiz(Quiz quiz) {
        var dto = quizMapper.convertQuizToDTO(quiz);
        quizRepository.save(dto);
        return dto.getId();
    }

    public Quiz getById(long id) {
            var founded = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
            return quizMapper.convertDTOtoQuiz(founded);
    }

    public List<Quiz> getAllQuizzes() {
        var result = new ArrayList<Quiz>();
        quizRepository.findAll().forEach(
                quizDTO -> result.add(quizMapper.convertDTOtoQuiz(quizDTO))
        );
        return result;
    }

    public boolean checkAnswer(int quizId, List<Integer> result) {
        var answers = getById(quizId).getAnswer();
        if (answers == null) {
            answers = List.of();
        }
        var realAnswer = new HashSet<>(answers);
        return realAnswer.containsAll(result) && result.size() == realAnswer.size();
    }
}
