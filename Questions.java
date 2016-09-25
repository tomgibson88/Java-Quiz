package Quizmaker;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

public class Questions extends JFrame {

    public Questions() {
    }

    public File fileSelection(String instruction, File fileLocation) throws HeadlessException {
        //selects the folder to search using Swing
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        final JLabel statusbar
                = new JLabel("Output of your selection will go here");
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(fileLocation);
        fc.setDialogTitle(instruction);
        File directory = null;
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            directory = fc.getSelectedFile().getAbsoluteFile();
            statusbar.setText("Selected file: " + directory.getAbsolutePath());

        } else {
            statusbar.setText("No file selected.");
        }
        fc.setVisible(true);
        return directory;
    }

    public Map<String, String> buildQuestionAndAnswer(File f) throws IOException {
        //Splits question and answers from text file and puts them in a map
        Map<String, String> qAndA = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String qText;
            while ((qText = reader.readLine()) != null) {
                String question = qText.split("\\?")[0].trim();
                String answer = qText.split("\\?")[1].trim();
                qAndA.put(question, answer);
            }
            reader.close();

        }
        return qAndA;
    }

    public String answer() {
        //allows user to enter answer
        Scanner input = new Scanner(System.in);
        String answerEntry = input.nextLine();
        return answerEntry;
    }

    public int answerQuestions(Map<String, String> qMap) {
        //checks if answer provided by user is correct and returns score
        int correctAnswerCount = 0;
        for (Map.Entry<String, String> entry : qMap.entrySet()) {
            System.out.println(entry.getKey());
            if (entry.getValue().equalsIgnoreCase(answer())) {
                System.out.println("This is correct.");
                correctAnswerCount++;
            } else {
                System.out.println("This is incorrect. The answer is "+entry.getValue().toLowerCase().trim());
            }
        }
        return correctAnswerCount;

    }

}
