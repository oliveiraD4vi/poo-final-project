package Labels;

public enum Color {
  black("Black"),
  blue("Blue"),
  red("Red"),
  white("White"),
  silver("Silver");

  private String name;

  private Color(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    return getName();
  }
}
