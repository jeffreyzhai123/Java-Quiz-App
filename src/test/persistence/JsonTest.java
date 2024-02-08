package persistence;

import model.Question;
import model.Quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkQuestion(String prompt, String hint, String answer, int number, Question question) {
        assertEquals(prompt, question.getPrompt());
        assertEquals(hint, question.getHint());
        assertEquals(answer, question.getAnswer());
        assertEquals(number, question.getNumber());
    }
}
