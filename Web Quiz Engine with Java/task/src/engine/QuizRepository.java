package engine;

import engine.dto.QuizDTO;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<QuizDTO, Long> {
}
