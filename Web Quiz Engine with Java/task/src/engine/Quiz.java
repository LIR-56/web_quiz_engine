package engine;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Quiz {
    private final String title;
    private final String text;
    private final List<String> options;
    private final int answer;

    @JsonCreator
    public Quiz(String title, String text, List<String> options, int answer) {
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

    public int getAnswer() {
        return answer;
    }
}
