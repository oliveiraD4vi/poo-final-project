package Persons;

public class Person {
  private String name;
  private String CPF;
  private String email;
  private String phone;

  public Person(String name, String CPF, String email, String phone) {
    this.name = name;
    this.CPF = CPF;
    this.email = email;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public String getCPF() {
    return CPF;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String toString() {
    return (
      getName() + " | "
      + getCPF() + "\n"
      + getEmail() + " | "
      + getPhone()
    );
  }
}
