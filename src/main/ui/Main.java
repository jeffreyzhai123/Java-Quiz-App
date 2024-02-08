package ui;

import java.io.FileNotFoundException;

// EFFECTS: runs the GUI version of the application
public class Main {
    public static void main(String[] args) {
        /*
        try {
            new QuizApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        */
        new Menu();
    }
}


