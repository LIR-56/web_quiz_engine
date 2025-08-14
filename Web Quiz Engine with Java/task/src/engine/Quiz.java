package engine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class Quiz {

    @JsonIgnore
    private long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    private List<String> options;

    //@NotNull
    private List<Integer> answer;

    public Quiz() {
    }

    @JsonCreator
    public Quiz(long id, String title, String text, List<String> options, List<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
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

    public List<Integer> getAnswer() {
        return answer;
    }

    public long getId() {
        return id;
    }
}
