package Persons;

public class Client extends Person implements Comparable<Client> {
  public Client(String name, String CPF, String email, String phone) {
    super(name, CPF, email, phone);
  }
  @Override public int compareTo(Client c){
    return this.getName().compareTo(c.getName());
  }
}
