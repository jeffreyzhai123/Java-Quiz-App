package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a quiz having a total score and a list of questions
public class Quiz implements Writable {

    private ArrayList<Question> questions;
    private String name;

    //getters

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: creates a quiz with an empty list of questions and the given name
    public Quiz(String name) {
        this.questions = new ArrayList<>();
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a question to the end of the list if it is not already added else do nothing
    public void addQuestion(Question q) {
        if (!questions.contains(q)) {
            this.questions.add(q);
            EventLog.getInstance().logEvent(new Event("Question added."));
        }
    }

    // REQUIRES: the question is already in the list
    // MODIFIES: this
    // EFFECTS: removes the question with the given number from the quiz
    public void removeQuestion(int no) {
        for (Question q : this.questions) {
            if (q.getNumber() == no) {
                this.questions.remove(q);
                EventLog.getInstance().logEvent(new Event("Question removed."));
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reset the list of questions making it empty
    public void reset() {
        this.questions = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("questions", questionsToJson());
        return json;
    }

    // EFFECTS: returns things in this quiz as a JSON array
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question q : questions) {
            jsonArray.put(q.toJson());
        }
        return jsonArray;
    }
}



