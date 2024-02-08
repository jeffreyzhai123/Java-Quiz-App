package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a question having a prompt, a hint, a difficulty rating, a score, and a correct answer
public class Question implements Writable {

    private String prompt;
    private String hint;
    private String answer;
    private int number;

    // REQUIRES: prompt, hint and answer cannot be an empty string and
    // number cannot already be in use for another question
    // EFFECTS: creates a question with the given prompt, hint, answer and number
    public Question(String prompt, String hint, String answer, int number) {
        this.prompt = prompt;
        this.hint = hint;
        this.answer = answer;
        this.number = number;
    }

    // getters

    public String getPrompt() {
        return prompt;
    }

    public String getHint() {
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public int getNumber() {
        return number;
    }

    // EFFECTS: check if the given answer matches the correct answer and
    // return true if the given answer is correct and false otherwise
    public boolean checkAnswer(String ans) {
        EventLog.getInstance().logEvent(new Event("Answer checked."));
        return this.answer.equalsIgnoreCase(ans);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("hint", hint);
        json.put("answer", answer);
        json.put("number", number);
        return json;
    }
}
