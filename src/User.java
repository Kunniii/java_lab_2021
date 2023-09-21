public class User {
  private String firstName;
  private String lastName;
  private int examScore;

  User() {
  }

  User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getExamScore() {
    return examScore;
  }

  public void setExamScore(int examScore) {
    this.examScore = examScore;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFullName() {
    return this.firstName + this.lastName;
  }

  public void updateInfo(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
