import java.util.ArrayList;

public class Quiz {
  private String type;
  private String question;
  private ArrayList<String> choices;
  private String answer;
  private int score;

  public Quiz(String type, String question, ArrayList<String> choices, String answer, int score) {
    this.type = type;
    this.question = question;
    this.choices = choices;
    this.answer = answer;
    this.score = score;
  }

  public Quiz(String type, String question, String answer, int score) {
    this.type = type;
    this.question = question;
    this.answer = answer;
    this.score = score;
  }

  public String getType() {
    return type;
  }

  public String getAnswer() {
    return answer;
  }

  public ArrayList<String> getChoices() {
    return choices;
  }

  public String getQuestion() {
    return question;
  }

  public int getScore() {
    return score;
  }

  public boolean isCorrectAnswer(String answer) {
    return this.getAnswer().equalsIgnoreCase(answer);
  }

  public void showQuestion() {
    switch (this.type) {
      case "TF":
      case "SA":
        System.out.println(this.getQuestion());
        break;
      case "MC":
        System.out.println(this.getQuestion());
        for (int i = 0; i < this.getChoices().size(); ++i) {
          System.out.println((char) (65 + (i)) + ") " + this.getChoices().get(i));
        }
        break;
    }
  }

}
