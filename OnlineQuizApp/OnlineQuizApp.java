import java.util.*;
import java.io.*;

class Question {
    String question;
    String optionA, optionB, optionC, optionD;
    char correctAnswer;

    Question(String question, String optionA, String optionB, String optionC, String optionD, char correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = Character.toUpperCase(correctAnswer);
    }

    void display() {
        System.out.println("\n" + question);
        System.out.println("A) " + optionA);
        System.out.println("B) " + optionB);
        System.out.println("C) " + optionC);
        System.out.println("D) " + optionD);
    }
}

public class OnlineQuizApp {
    static ArrayList<Question> questions = new ArrayList<>();
    static TreeMap<Integer, String> leaderboard = new TreeMap<>(Collections.reverseOrder());
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadQuestions();   // Load questions from a function
        System.out.println("===== ONLINE QUIZ APPLICATION =====");

        System.out.print("Enter your name: ");
        String username = sc.nextLine();

        int score = startQuiz();
        leaderboard.put(score, username);

        System.out.println("\n===== Quiz Completed =====");
        System.out.println("Your Score: " + score);

        showLeaderboard();
    }

    // ---------------------------------------------------------
    // LOAD SAMPLE QUESTIONS
    // ---------------------------------------------------------
    static void loadQuestions() {
        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                "super", "extends", "implements", "import", 'B'
        ));
        questions.add(new Question(
                "Which data structure uses FIFO?",
                "Stack", "Queue", "Tree", "Graph", 'B'
        ));
        questions.add(new Question(
                "Which method starts a thread?",
                "start()", "run()", "execute()", "thread()", 'A'
        ));
        questions.add(new Question(
                "Which collection stores unique values only?",
                "List", "Queue", "Set", "Map", 'C'
        ));
        questions.add(new Question(
                "Which OOP principle hides internal details?",
                "Polymorphism", "Abstraction", "Overloading", "Recursion", 'B'
        ));
    }

    // ---------------------------------------------------------
    // START QUIZ FUNCTION
    // ---------------------------------------------------------
    static int startQuiz() {
        int score = 0;

        for (Question q : questions) {
            q.display();

            System.out.println("You have 10 seconds to answer...");
            long startTime = System.currentTimeMillis();

            String ans = "";
            while ((System.currentTimeMillis() - startTime) < 10000 && ans.isEmpty()) {
                try {
                    if (System.in.available() > 0) {
                        ans = sc.nextLine().trim().toUpperCase();
                    }
                } catch (Exception e) {
                    ans = "";
                }
            }

            if (ans.isEmpty()) {
                System.out.println("Time up! No answer recorded.");
            } else if (ans.charAt(0) == q.correctAnswer) {
                System.out.println("✔ Correct!");
                score++;
            } else {
                System.out.println("✘ Wrong! Correct answer: " + q.correctAnswer);
            }
        }

        return score;
    }

    // ---------------------------------------------------------
    // LEADERBOARD FUNCTION
    // ---------------------------------------------------------
    static void showLeaderboard() {
        System.out.println("\n===== LEADERBOARD =====");
        int rank = 1;
        for (Map.Entry<Integer, String> entry : leaderboard.entrySet()) {
            System.out.println(rank + ". " + entry.getValue() + " - Score: " + entry.getKey());
            rank++;
        }
    }
}
