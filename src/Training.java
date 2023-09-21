import java.util.Scanner;

public class Training {
  private int totalScore;
  private Bank bank;
  private Scanner scanner;

  public Training(Bank bank, Scanner sc) {
    this.bank = bank;
    this.scanner = sc;
  }

  private String sanitizeUserAnswer(String input, String quizType) throws Exception {
    input = input.trim();
    switch (quizType) {
      case "SA":
        return input;
      case "TF":
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
          return input;
        } else {
          throw new Exception("Only support `true` or `false`");
        }

      case "MC":
        char c = input.charAt(0);
        int charCode = (int) c;
        if (charCode < 65 && charCode > 73) {
          throw new Exception("Only support answer from A to I");
        } else {
          return String.valueOf(c);
        }
      default:
        if (input.equalsIgnoreCase("SKIP")) {
          return "SKIP";
        }
    }
    return "";
  }

  public void start(User user) {
    System.out.println("\n=== Start Training ===");
    for (Quiz quiz : this.bank.getAllQuiz()) {
      System.out.println("POINT: " + quiz.getScore());
      System.out.print("QUESTION: ");
      quiz.showQuestion();
      System.out.print(">>> ");
      boolean isInputSafe = false;
      String userAnswer = "";
      while (!isInputSafe) {
        userAnswer = this.scanner.nextLine().trim();
        try {
          userAnswer = this.sanitizeUserAnswer(userAnswer, quiz.getType());
          isInputSafe = true;
        } catch (Exception e) {
          System.out.println("[!] ERROR: " + e.getMessage());
        }
      }

      // Skip the question
      if (userAnswer.equalsIgnoreCase("SKIP")) {
        System.out.println("You have select to skip that question!");
        continue;
      }

      int quizScore = quiz.getScore();
      String answer = quiz.getAnswer();

      // Check if the given answer is correct
      if (quiz.isCorrectAnswer(userAnswer)) {
        System.out.println("\nCorrect! You get " + quizScore + " points!");
        totalScore += quizScore;
      }
      // If the answer is wrong
      else {
        System.out.println("\nIncorrect! The answer was " + answer + ". You lose " + quizScore + " points!");
        totalScore -= quizScore;
      }
      this.scanner.nextLine();
      System.out.println("\n---------------------------\n");
    }

    this.scanner.close();
    user.setExamScore(totalScore);
  }
}
