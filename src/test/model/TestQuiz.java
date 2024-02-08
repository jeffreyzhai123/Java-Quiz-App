package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuiz {
    private Quiz testQuiz;
    private Question testQuestion1;
    private Question testQuestion2;
    private Question testQuestion3;

    @BeforeEach
    public void runBefore() {
        testQuiz = new Quiz("quiz 1");
        testQuestion1 = new Question("1 + 1 + 2 = ", "2 = 1 + 1", "4", 1);
        testQuestion2 = new Question("2 * 4 = ", "2 + 2 + 2 + 2", "8", 2);
        testQuestion3 = new Question("4 - 2 * 5 = ", "follow BEDMAS", "-6", 3);
    }

    @Test
    public void testGetQuestions() {
        assertEquals(0, testQuiz.getQuestions().size());
        testQuiz.addQuestion(testQuestion1);
        testQuiz.addQuestion(testQuestion2);
        testQuiz.addQuestion(testQuestion3);
        assertEquals(3, testQuiz.getQuestions().size());
        assertTrue(testQuiz.getQuestions().contains(testQuestion1));
        assertTrue(testQuiz.getQuestions().contains(testQuestion2));
        assertTrue(testQuiz.getQuestions().contains(testQuestion3));
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testQuiz.getQuestions().size());
        assertEquals("quiz 1", testQuiz.getName());
    }

    @Test
    public void testAddQuestion() {
        testQuiz.addQuestion(testQuestion1);
        assertEquals(1, testQuiz.getQuestions().size());
        assertTrue(testQuiz.getQuestions().contains(testQuestion1));
        assertFalse(testQuiz.getQuestions().contains(testQuestion2));
        assertFalse(testQuiz.getQuestions().contains(testQuestion3));

        // adding duplicate questions
        testQuiz.addQuestion(testQuestion1);
        assertEquals(1, testQuiz.getQuestions().size());

        // adding multiple questions
        testQuiz.addQuestion(testQuestion2);
        testQuiz.addQuestion(testQuestion3);
        assertEquals(3, testQuiz.getQuestions().size());
        assertTrue(testQuiz.getQuestions().contains(testQuestion1));
        assertTrue(testQuiz.getQuestions().contains(testQuestion2));
        assertTrue(testQuiz.getQuestions().contains(testQuestion3));
    }

    @Test
    public void testRemoveQuestion(){
        testQuiz.addQuestion(testQuestion1);
        testQuiz.removeQuestion(2);
        assertEquals(1, testQuiz.getQuestions().size());

        //removing multiple questions
        testQuiz.addQuestion(testQuestion1);
        testQuiz.addQuestion(testQuestion2);
        testQuiz.addQuestion(testQuestion3);
        testQuiz.removeQuestion(1);
        testQuiz.removeQuestion(2);
        assertEquals(1, testQuiz.getQuestions().size());
        assertFalse(testQuiz.getQuestions().contains(testQuestion1));
        assertFalse(testQuiz.getQuestions().contains(testQuestion2));
        assertTrue(testQuiz.getQuestions().contains(testQuestion3));
    }

    @Test
    public void testReset() {
        testQuiz.addQuestion(testQuestion1);
        testQuiz.addQuestion(testQuestion2);
        testQuiz.addQuestion(testQuestion3);
        assertEquals(3, testQuiz.getQuestions().size());
        testQuiz.reset();
        assertEquals(0, testQuiz.getQuestions().size());
        assertFalse(testQuiz.getQuestions().contains(testQuestion1));
        assertFalse(testQuiz.getQuestions().contains(testQuestion2));
        assertFalse(testQuiz.getQuestions().contains(testQuestion3));
    }
}
