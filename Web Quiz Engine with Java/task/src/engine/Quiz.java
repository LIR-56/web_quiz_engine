package engine;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Quiz {
    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String text;

    @NotNull
    @Size(min = 2)
    private final List<String> options;

    //@NotNull
    private final List<Integer> answer;

    @JsonCreator
    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
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
}
