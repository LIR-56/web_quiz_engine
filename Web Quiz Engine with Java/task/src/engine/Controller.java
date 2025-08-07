package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final QuizService quizService;
    private final ObjectMapper om;

    private final String correctAnswer;
    private final String wrongAnswer;

    public Controller(@Autowired QuizService quizService,
                      @Autowired ObjectMapper om) throws JsonProcessingException {

        this.quizService = quizService;
        this.om = om;

        correctAnswer = om.writeValueAsString(new AnswerResponse(true, "Congratulations, you're right"));
        wrongAnswer = om.writeValueAsString(new AnswerResponse(false, "Wrong answer! Please, try again."));
    }

    @RequestMapping(value = "/api/quizzes", method = RequestMethod.POST)
    public String addQuiz(
            @RequestBody Quiz quiz
    ) throws JsonProcessingException {
        int id = quizService.addQuiz(quiz);
        return om.writeValueAsString(QuizResponse.fromQuiz(id , quiz));
    }

    @RequestMapping(value = "/api/quizzes/{id}", method = RequestMethod.GET)
    public String getQuiz(
            @PathVariable int id
    ) throws JsonProcessingException {
        return om.writeValueAsString(QuizResponse.fromQuiz(id, quizService.getById(id)));
    }

    @RequestMapping(value = "/api/quizzes", method = RequestMethod.GET)
    public String getAllQuizes() throws JsonProcessingException {
        var result = new ArrayList<QuizResponse>();
        var quizzes = quizService.getAllQuizes();
        for (int i = 0; i < quizzes.size(); i++) {
            result.add(QuizResponse.fromQuiz(i, quizzes.get(i)));
        }
        return om.writeValueAsString(result);
    }

    @RequestMapping(value = "/api/quizzes/{id}/solve", method = RequestMethod.POST)
    public String solve(
            @PathVariable @NonNull Integer id,
            @RequestParam @NonNull Integer answer
    ) {
        if (quizService.checkAnswer(id, answer)) {
            return correctAnswer;
        } else {
            return wrongAnswer;
        }
    }

    private record AnswerResponse(@JsonProperty("success") boolean success, String feedback) {}
    private record QuizResponse(int id, String title, String text, List<String> options) {

        static QuizResponse fromQuiz(int id, Quiz quiz) {
            return new QuizResponse(id, quiz.getTitle(), quiz.getText(), quiz.getOptions());
        }
    }

    @ControllerAdvice
    public static class DefaultExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(QuizNotFoundException.class)
        @ResponseBody
        public ResponseEntity<String> quizNotFound() {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
