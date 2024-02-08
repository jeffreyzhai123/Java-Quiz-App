package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestQuestion {
    private Question testQuestion;
    private Question testQuestion2;

    @BeforeEach
    public void runBefore() {
        testQuestion = new Question("2 + 2 * 4 = ", "follow BEDMAS", "10", 1);
        testQuestion2 = new Question("My name is = ", "starts with J", "Jeffrey", 2);
    }

    @Test
    public void testConstructor() {
        assertEquals("2 + 2 * 4 = ", testQuestion.getPrompt());
        assertEquals("follow BEDMAS", testQuestion.getHint());
        assertEquals("10", testQuestion.getAnswer());
        assertEquals(1, testQuestion.getNumber());

        assertEquals("My name is = ", testQuestion2.getPrompt());
        assertEquals("starts with J", testQuestion2.getHint());
        assertEquals("Jeffrey", testQuestion2.getAnswer());
        assertEquals(2, testQuestion2.getNumber());
    }



    @Test
    public void testCheckAnswer() {
        assertFalse(testQuestion.checkAnswer("11"));
        assertFalse(testQuestion.checkAnswer("11 "));
        assertTrue(testQuestion.checkAnswer("10"));
    }

    @Test
    public void testCheckAnswerCaseInsensitive() {
        assertTrue(testQuestion2.checkAnswer("JEFFREY"));
        assertTrue(testQuestion2.checkAnswer("jeffrey"));
        assertTrue(testQuestion2.checkAnswer("jEfFrEy"));
        assertFalse(testQuestion2.checkAnswer("Bob"));
    }
}
