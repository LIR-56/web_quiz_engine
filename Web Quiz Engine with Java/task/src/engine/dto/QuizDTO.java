package engine.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;

import java.util.List;

@Entity
@Table(name = "quizzes")
public class QuizDTO {

    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "question_text")
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> options;

    @Nullable
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> answer;

    public QuizDTO() {
    }

    public QuizDTO(long id, String title, String text, List<String> options, @Nullable List<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    @Nullable
    public List<Integer> getAnswer() {
        return answer;
    }
}
