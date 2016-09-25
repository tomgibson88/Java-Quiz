package Quizmaker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class QuizMainProcessor {

    public static void main(String[] args) {
        Questions q = new Questions();
        final File LOCATION = new File("C:\\My Documents");
        //Selects the question file
        File quizFile = q.fileSelection("Please select the question text file", LOCATION);
        try {
            //Builds the question and answers from the file of choice
            q.buildQuestionAndAnswer(quizFile);
            //User answers questions line by line from file in System.out
            int correctAnswers = q.answerQuestions(q.buildQuestionAndAnswer(quizFile));
            int lines = 0;
            LineNumberReader reader = new LineNumberReader(new FileReader(quizFile));

            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {
                lines = reader.getLineNumber();
            }
            //prints out score
            System.out.println("Your score is " + correctAnswers + " out of " + lines);
        } catch (IOException ex) {
            System.err.println("There has been an IOException when reading the text file: " + quizFile.getName());
        }
        System.exit(0);
    }
}
