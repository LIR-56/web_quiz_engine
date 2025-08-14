package engine;

import engine.dto.QuizDTO;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizMapper() {
    }

    public QuizDTO convertQuizToDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
    }


    public Quiz convertDTOtoQuiz(QuizDTO quizDTO) {
        return new Quiz(quizDTO.getId(),
                quizDTO.getTitle(),
                quizDTO.getText(),
                quizDTO.getOptions(),
                quizDTO.getAnswer());
    }
}
