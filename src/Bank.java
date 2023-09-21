import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
  private int size;
  private ArrayList<Quiz> quizzes = new ArrayList<>();

  public int size() {
    return this.size;
  }

  public ArrayList<Quiz> getAllQuiz() {
    return this.quizzes;
  }

  public void add(Quiz q) {
    this.quizzes.add(q);
  }

  public boolean load(String filePath) {
    try {
      FileReader fileReader = new FileReader(filePath);
      BufferedReader file = new BufferedReader(fileReader);

      String line = file.readLine();
      this.size = Integer.parseInt(line);

      while ((line = file.readLine()) != null) {
        Metadata meta = new Metadata(line);
        String question, answer;

        switch (meta.type) {
          case "TF":
          case "SA":
            question = file.readLine();
            answer = file.readLine();
            this.add(new Quiz(meta.type, question, answer, meta.score));
            break;

          case "MC":
            question = file.readLine();
            ArrayList<String> choices = new ArrayList<>();
            int c = Integer.parseInt(file.readLine());

            for (int i = 0; i != c; --c) {
              String choice = file.readLine();
              choices.add(choice);
            }
            answer = file.readLine();
            this.add(new Quiz(meta.type, question, choices, answer, meta.score));
            break;

          default:
            break;
        }
      }

      file.close();
      fileReader.close();

      System.out.println("[+] Loaded " + this.size() + " questions");
      return true;

    } catch (IOException e) {
      System.out.println("[!] ERROR: " + e.getMessage());
      return false;
    }
  }

  public Bank getRandomQuizzes(String number) throws Exception {
    int n = 0;
    try {
      n = Integer.parseInt(number);
      if (n <= 0) {
        throw new Exception("Sorry, that is not valid.");
      }
      if (n == this.size) {
        return this;
      }
    } catch (Exception e) {
      throw new Exception("Sorry, that is not valid.");
    }
    Bank randomBank = new Bank();
    ArrayList<Quiz> copiedList = new ArrayList<>(this.quizzes);

    if (n > this.size()) {
      throw new Exception("Sorry, that is too many.");
    }

    for (int x = 0; x < n; ++x) {
      int index = new Random().nextInt(copiedList.size());
      randomBank.add(copiedList.get(index));
      copiedList.remove(index);
    }

    return randomBank;
  }
}

class Metadata {
  public final String type;
  public final int score;

  public Metadata(String s) {
    String[] parts = s.split("\\s+");
    this.type = parts[0];
    this.score = Integer.parseInt(parts[1]);
  }
}