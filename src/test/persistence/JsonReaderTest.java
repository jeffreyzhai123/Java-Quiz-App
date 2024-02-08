package persistence;

import model.Quiz;
import model.Question;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz q = reader.read();
            fail ("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals(0, q.getQuestions().size());
            assertEquals("My quiz", q.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testGeneralQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQuiz.json");
        try {
            Quiz q = reader.read();
            List<Question> questions = q.getQuestions();
            assertEquals (2, questions.size());
            assertEquals("My quiz", q.getName());
            checkQuestion("2 + 2 =", "4?", "4", 1, questions.get(0));
            checkQuestion("3 + 3 =", "6?", "6", 2, questions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
