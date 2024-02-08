package persistence;

import model.Question;
import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            Quiz q = new Quiz("My quiz");
            JsonWriter writer = new JsonWriter("./datamy\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyQuiz () {
        try {
            Quiz q = new Quiz("My quiz");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQuiz.json");
            q = reader.read();
            assertEquals(0, q.getQuestions().size());
            assertEquals("My quiz", q.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralQuiz() {
        try {
            Quiz q = new Quiz("My quiz");
            q.addQuestion(new Question("2 + 2 =", "4?", "4", 1));
            q.addQuestion(new Question("3 + 3 =", "6?", "6", 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQuiz.json");
            q = reader.read();
            List<Question> questions = q.getQuestions();
            assertEquals(2, questions.size());
            assertEquals("My quiz", q.getName());
            checkQuestion("2 + 2 =", "4?", "4", 1, questions.get(0));
            checkQuestion("3 + 3 =", "6?", "6", 2, questions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
