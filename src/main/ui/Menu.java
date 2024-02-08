package ui;

import model.Event;
import model.EventLog;
import model.Quiz;
import model.Question;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.io.File; (used for debugging)

// Represents application's main window frame
public class Menu extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/quiz.json";

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private final JButton quizButton = new JButton("Start Quiz");
    private final JButton addButton = new JButton("Add Question");
    private final JButton removeButton = new JButton("Remove Question");
    private final JButton saveButton = new JButton("Save Quiz");
    private final JButton loadButton = new JButton("Load Quiz");
    private final JButton displayButton = new JButton("Display Questions");
    private final JButton quitButton = new JButton("Quit Application");
    private final JButton enter2 = new JButton("Enter");
    private final JButton enter3 = new JButton("Enter");
    private final JButton enter4 = new JButton("Enter");

    private Quiz quiz;
    private final JDesktopPane desktop;

    private JTextField field;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;

    private String userAnswer;
    private JFrame quizFrame;


    /*
    EFFECTS: Constructor sets up button panel
     */

    public Menu() {
        String name;
        name = "Quiz";
        quiz = new Quiz(name);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.PINK);
        desktop.addMouseListener(new DesktopFocusAction());

        setContentPane(desktop);
        setTitle("CPSC 210: " + quiz.getName());
        setSize(WIDTH, HEIGHT);

        displayMainMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: displays main menu options for user
    private void displayMainMenu() {
        setLayout(new FlowLayout());
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(quizButton);
        buttons.add(removeButton);
        buttons.add(addButton);
        buttons.add(saveButton);
        buttons.add(loadButton);
        buttons.add(displayButton);
        buttons.add(quitButton);

        for (JButton b : buttons) {
            add(b);
            b.setActionCommand(b.getText());
            b.addActionListener(this);
        }
    }

    //EFFECTS: Helper to centre main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    //EFFECTS: processes user actions and calls corresponding functions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start Quiz")) {
            runQuiz(quiz);
        } else if (e.getActionCommand().equals("Save Quiz")) {
            saveStatus();
        } else if (e.getActionCommand().equals("Add Question")) {
            displayAddMenu();
        } else if (e.getActionCommand().equals("Remove Question")) {
            displayRemoveMenu(quiz);
        } else if (e.getActionCommand().equals("Display Questions")) {
            displayQuestions(quiz);
        } else if (e.getActionCommand().equals("Quit Application")) {
            quitQuiz();
        } else {
            loadStatus();
        }
    }


    //EFFECTS: Represents action to be taken when user clicks desktop
    // to switch focus.
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Menu.this.requestFocusInWindow();
        }
    }

    // EFFECTS: Quits the application and prints out the events logged to the console
    private void quitQuiz() {
        EventLog el = EventLog.getInstance();

        for (Event event : el) {
            System.out.println(event.getDate() + " " + event.getDescription());
        }
        System.exit(0);
    }

    //EFFECTS: allows the user to save their file
    private void saveStatus() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            JFrame displayFrame = new JFrame("Save successful!");
            JLabel successLabel = new JLabel("Your status has been saved!");
            displayFrame.add(successLabel);
            setDefaultFields(displayFrame);
        } catch (FileNotFoundException e) {
            JFrame displayFrame = new JFrame("Save unsuccessful!");
            JLabel unsuccessfulLabel = new JLabel("An error occurred, try again.");
            displayFrame.add(unsuccessfulLabel);
            setDefaultFields(displayFrame);
        }
    }

    //EFFECTS: allows user to load their previously saved file and if successful show image raccoon.jpg
    private void loadStatus() {
        try {
            quiz = jsonReader.read();
            JFrame displayFrame = new JFrame("Load successful!");
            JLabel successLabel = new JLabel("Your status has been loaded!");
            ImageIcon image = new ImageIcon("./data/raccoon.jpg");
            JLabel imageLabel = new JLabel(image);

            displayFrame.add(successLabel);
            displayFrame.add(imageLabel);

            setDefaultFields(displayFrame);
        } catch (IOException e) {
            JFrame displayFrame = new JFrame("Load unsuccessful!");
            JLabel unsuccessfulLabel = new JLabel("Unable to read from file " + JSON_STORE);
            displayFrame.add(unsuccessfulLabel);
            setDefaultFields(displayFrame);
        }
    }

    // EFFECTS: shows user all questions stored in quiz
    private void displayQuestions(Quiz quiz) {
        JFrame displayFrame = new JFrame("Questions");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Question q : quiz.getQuestions()) {
            listModel.addElement(q.getPrompt());
        }
        JList<String> prompts = new JList<>(listModel);
        displayFrame.add(prompts);
        setDefaultFields(displayFrame);
    }

    //EFFECTS: helper function to create field and label for prompt input
    private JLabel promptLabel() {
        JLabel label = new JLabel("Enter prompt");
        field = new JTextField(10);
        return label;
    }

    //EFFECTS: helper function to create field and label for hint input
    private JLabel hintLabel() {
        JLabel label2 = new JLabel("Enter hint");
        field2 = new JTextField(10);
        return label2;
    }

    //EFFECTS: helper function to create field and label for answer input
    private JLabel answerLabel() {
        JLabel label3 = new JLabel("Enter answer");
        field3 = new JTextField(10);
        return label3;
    }

    //EFFECTS: helper function to create field and label for number input
    private JLabel numberLabel() {
        JLabel label4 = new JLabel("Enter number");
        field4 = new JTextField(10);
        return label4;
    }

    // MODIFIES: this, quiz
    // EFFECTS: initializes the add question window, allows user to add a question

    private void displayAddMenu() {
        JFrame addFrame = new JFrame("Add a question");
        JButton enter = new JButton("Enter");
        enter.setActionCommand("set prompt");

        addFrame.add(promptLabel());
        addFrame.add(field);
        addFrame.add(enter);
        setDefaultFields(addFrame);

        addFrame.add(hintLabel());
        addFrame.add(field2);
        addFrame.add(enter2);

        addFrame.add(answerLabel());
        addFrame.add(field3);
        addFrame.add(enter3);

        addFrame.add(numberLabel());
        addFrame.add(field4);
        addFrame.add(enter4);

        enter.addActionListener(e -> {
            quiz.addQuestion(new Question(field.getText(), field2.getText(), field3.getText(),
                    Integer.parseInt(field4.getText())));
            addFrame.dispose();
        });
    }

    //MODIFIES: this, quiz
    //EFFECTS: deletes the user-selected questions from the quiz
    private void displayRemoveMenu(Quiz quiz) {
        JFrame deleteQuestionFrame = new JFrame("Delete a question");
        JButton b = new JButton("Delete");

        JLabel label5 = new JLabel("Enter question number");
        JButton enter5 = new JButton("Enter");
        field5 = new JTextField(10);
        deleteQuestionFrame.add(label5);
        deleteQuestionFrame.add(field5);
        deleteQuestionFrame.add(enter5);

        b.addActionListener(e -> {
            quiz.removeQuestion(Integer.parseInt(field5.getText()));
            deleteQuestionFrame.dispose();

        });

        deleteQuestionFrame.add(b);
        setDefaultFields(deleteQuestionFrame);
    }

    //EFFECTS: runs and displays the quiz if there is at least 1 question in the quiz
    private void runQuiz(Quiz quiz) {
        ArrayList<Question> questions = quiz.getQuestions();
        quizFrame = new JFrame();
        setDefaultFields(quizFrame);
        quizFrame.setMinimumSize(new Dimension(500, 400));
        JTabbedPane tp = new JTabbedPane();
        if (questions.size() > 0) {
            createPane(questions, tp);
            quizFrame.add(tp);
        } else {
            quizFrame.add(new JLabel("Not enough questions, need at least 1"));
        }
    }

    // MODIFIES: frame
    // EFFECTS: sets default parameters, resizable, pack, visible, (min/preferred) size, layout
    private void setDefaultFields(JFrame frame) {
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setMinimumSize(new Dimension(200, 200));
    }

    // EFFECTS: creates panels with the question, a text field for the user's answer.
    private void createPane(ArrayList<Question> questions, JTabbedPane tp) {
        for (Question q : questions) {

            JButton b = new JButton("Check");
            JTextField userInput = new JTextField(5);
            b.addActionListener(e -> {
                userAnswer = userInput.getText();
                userCorrect(q, userAnswer, q.getHint());
            });

            JPanel panel = new JPanel();
            panel.add(new JLabel(q.getPrompt()));

            panel.add(userInput);
            panel.add(b);
            panel.setPreferredSize(new Dimension(500, 300));
            tp.add(panel);

        }
    }

    // EFFECTS: checks if user answer is correct, if correct shows correct, otherwise shows hint.
    private void userCorrect(Question q, String userAnswer, String hint) {
        JFrame result = new JFrame();
        JLabel label = new JLabel();
        JLabel hints = new JLabel();

        if (q.checkAnswer(userAnswer)) {
            label.setText("Correct!");
            result.add(label);
            setDefaultFields(result);

        } else {
            hints.setText("Hint: " + hint);
            result.add(hints);
            setDefaultFields(result);
        }
    }

    //EFFECTS: starts the application
    public static void main(String[] args) {
        new Menu();
    }
}
