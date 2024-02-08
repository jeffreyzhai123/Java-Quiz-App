package ui;

import model.Quiz;
import model.Question;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

// Quiz application representation
public class QuizApp {

    private Quiz quiz;
    private Scanner input;
    private static final String JSON_STORE = "./data/quiz.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the quiz application
    public QuizApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runQuiz();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runQuiz() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Quitting program");
    }

    // MODIFIES: this
    // EFFECTS: takes input from the user to initialize the quiz and create a list of questions
    private void init() {
        System.out.println("Enter name for your quiz");
        String name;
        Scanner s4 = new Scanner(System.in);
        name = s4.nextLine();
        quiz = new Quiz(name);

        String ans;
        System.out.println("please make a question");
        ans = "y";

        while (ans.equals("y")) {
            Question q = new Question(inputPrompt(), inputHint(), inputAnswer(), inputNumber());
            quiz.addQuestion(q);
            System.out.println("do you want to keep making questions, y for yes n for no");
            Scanner s = new Scanner(System.in);
            ans = s.next();
        }
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: take user input for the prompt
    public String inputPrompt() {
        System.out.println("Enter prompt:");
        String prompt;
        Scanner s = new Scanner(System.in);
        prompt = s.nextLine();
        return prompt;
    }

    // EFFECTS: take user input for the hint
    public String inputHint() {
        System.out.println("Enter hint:");
        String hint;
        Scanner s = new Scanner(System.in);
        hint = s.nextLine();
        return hint;
    }

    // EFFECTS: take user input for the answer
    public String inputAnswer() {
        System.out.println("Enter answer");
        String answer;
        Scanner s = new Scanner(System.in);
        answer = s.nextLine();
        return answer;
    }

    // REQUIRES: the number entered must not already be entered to a previous question
    // EFFECTS: take user input for question number
    public int inputNumber() {
        System.out.println("Enter number");
        int num;
        Scanner s = new Scanner(System.in);
        num = s.nextInt();
        return num;
    }

    // EFFECTS: process user command before quiz is started
    private void processCommand(String command) {
        if (command.equals("s")) {
            startQuiz();
        } else if (command.equals("v")) {
            viewAllQuestions();
        } else if (command.equals("r")) {
            removeQuestion();
        } else if (command.equals("a")) {
            addQuestion();
        } else if (command.equals("res")) {
            resetQuestion();
        } else if (command.equals("save")) {
            saveQuiz();
        } else if (command.equals("load")) {
            loadQuiz();
        } else {
            System.out.println("Invalid selection, please select again");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("s -> Start quiz");
        System.out.println("v -> View all questions");
        System.out.println("q -> Quit program");
        System.out.println("r -> Remove question");
        System.out.println("a -> Add question");
        System.out.println("res -> Reset all questions");
        System.out.println("save -> save quiz to file");
        System.out.println("load -> load quiz from file");
    }

    // EFFECTS: starts the quiz for the user to complete
    private void startQuiz() {
        List<Question> questions = quiz.getQuestions();
        for (Question q : questions) {
            // provide prompt and scan answer
            System.out.println(q.getPrompt());
            Scanner s = new Scanner(System.in);
            String ans = s.nextLine();

            // prints hint if needed
            if (!q.checkAnswer(ans)) {
                System.out.println(q.getHint());
                Scanner s1 = new Scanner(System.in);
                String ans2 = s1.nextLine();

                // prints answer if still stuck
                if (!q.checkAnswer(ans2)) {
                    System.out.println(q.getAnswer());
                }
            } else {
                System.out.println("Answer is correct");
            }
            // quits quiz if input is q
            Scanner s2 = new Scanner(System.in);
            String quit = s2.nextLine();
            if (quit.equals("q")) {
                System.out.println("Quit quiz");
                break;
            }
        }
        System.out.println("Quiz ended");
    }

    // EFFECTS: prints all questions number by their index in the array for the user to view
    public void viewAllQuestions() {
        List<Question> questions = quiz.getQuestions();
        int i = 1;
        for (Question q : questions) {
            System.out.println("Questions " + i + " is " + q.getPrompt());
            i++;
        }
    }

    // REQUIRES: the question the user is trying to remove is already in the quiz
    // MODIFIES: this
    // EFFECTS: allows the user to remove a question from a quiz
    private void removeQuestion() {
        System.out.println("Enter the question number of the question that you wish to remove");
        Scanner s = new Scanner(System.in);
        int no = s.nextInt();
        quiz.removeQuestion(no);
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add a question to the quiz if the question is not already in the quiz
    private void addQuestion() {
        Question q = new Question(inputPrompt(), inputHint(), inputAnswer(), inputNumber());
        quiz.addQuestion(q);
    }

    // MODIFIES: this
    // EFFECTS: allows user to reset all questions
    private void resetQuestion() {
        quiz.reset();
    }

    // EFFECTS: saves the quiz to file
    private void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            System.out.println("Saved " + quiz.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads quiz from file
    private void loadQuiz() {
        try {
            quiz = jsonReader.read();
            System.out.println("Loaded " + quiz.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}





