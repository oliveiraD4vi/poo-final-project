package Labels;

public enum Brand {
  fiat("Fiat"),
  chevrolet("Chevrolet"),
  volkswagen("Volkswagen"),
  renault("Renault"),
  hyundai("Hyundai"),
  yamaha("Yamaha"),
  honda("Honda");

  private String name;

  private Brand(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    return getName();
  }
}
