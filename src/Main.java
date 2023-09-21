import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("[?] What is your first name?\n>>> ");
    String firstName = scanner.nextLine();

    System.out.print("[?] What is your last name?\n>>> ");
    String lastName = scanner.nextLine();

    User user = new User(firstName, lastName);

    Bank masterBank = new Bank();
    Bank randomBank = new Bank();

    System.out.print("[?] What file stores your questions?\n>>> ");
    String path = scanner.nextLine();
    if (masterBank.load(path)) {
      String numberOfQuiz;
      while (true) {
        try {
          System.out.print("[?] How many questions would you like (out of " + masterBank.size() + ")\n>>> ");
          numberOfQuiz = scanner.nextLine();
          randomBank = masterBank.getRandomQuizzes(numberOfQuiz);
          break;
        } catch (Exception e) {
          System.out.println("[!] ERROR: " + e.getMessage());
          continue;
        }
      }
      Training training = new Training(randomBank, scanner);

      training.start(user);

      System.out.println("==========");
      System.out.println("[+] " + user.getFullName() + ", your game is over!");
      System.out.println("[+] Your final score is " + user.getExamScore() + " points.");
      System.out.println("[+] Good luck next time!");
      System.out.println("==========");
    }

    scanner.close();

  }
}
