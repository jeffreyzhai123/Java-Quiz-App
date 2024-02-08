# My Personal Project

## Memory Aid Project

<h3>What Will The Application Do?</h3>

The application will aid the user in their studies by 
helping them memorize information for exams.
The user will be able to create a **list of questions with 
corresponding hints** and a **list of answers**. 
The application will use the given inputs to 
create a quiz where it will **prompt** the user with the 
questions, **provide hints** when necessary let the users
input their answers and **check** for correctness. 

<h3>Who Will Use It</h3>

Potential user list:
- Students
- Tutors
- Teachers

<h3>Why Is This Project Of Interest</h3>

This project is interesting to me because I feel like 
I would actually use this kind of application myself.





## User Story Phase 0

As a user, I want to be able to add questions to my quiz

As a user, I want to be able to remove questions from my quiz

As a user, I want to be prompted with a hint if I get a question wrong on the first try

As a user, I want the answer of the question to be revealed if I am 
still incorrect after getting a hint

As a user, I want to get confirmation that my answer was correct if I get it on the first try

As a user, I want to be able to reset/remove all the questions in the quiz at once

As a user, I want to be able to view all the questions that I added in the quiz

As a user, I want to be able to quit the program by typing q

## User Story Phase 2

As a user, I want to be able to save my quiz if I so choose

As a user, I want to be able to be able to load my quiz from file if I so choose

## Instructions for Grader

You can generate the first required action of adding questions to a quiz by clicking the add button
and input the prompt, hint, answer, question number and then pressing the
enter button beside the prompt 

You can generate the second required action by clicking the display button and displaying the list of questions

You can remove questions by clicking the remove button and inputting the question number of the question
that you want to remove

You can run the quiz by clicking the start quiz button and view different questions using the tab at the top

You can locate my visual component by loading a quiz successfully

You can save by quiz by pressing the save button

You can load a saved quiz by pressing the load button

Note: when using the add button to add new questions only the first enter button works
which is intentional as the other enter buttons are just to align everything visually

## Phase 4 Task 2
Wed Aug 09 20:09:47 PDT 2023 Question added.

Wed Aug 09 20:09:53 PDT 2023 Question added.

Wed Aug 09 20:10:02 PDT 2023 Question removed.

Wed Aug 09 20:10:20 PDT 2023 Question checked.

## Phase 4 Task 3
If I had more time to work on the project I would refactor my hierarchy structure by having JsonReader
and JsonWriter implement Writable instead of having Quiz and Question implement Writable. The reason
is because it would vastly improve the coupling of the program as JsonWriter and JsonReader won't
need to know about Quiz and Question anymore.

I would also refactor my GUI class. Instead of making a brand new JFrame to display a message each time
I would just add a new JLabel and set it visible when needed. The reason for this is because it will make
the overall code less complex and readable.