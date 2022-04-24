/**
 * A classe Client representa uma determinada pessoa/
 * cliente do sistema.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 24/04/2022
 */

package Persons;

public class Client extends Person implements Comparable<Client> {
  public Client(String name, String CPF, String email, String phone) {
    super(name, CPF, email, phone);
  }

  /**
   * O método compareTo sobrescreve o método da implementação,
   * adicionando a lógica da comparação de um objeto com outro
   * do mesmo tipo.
   */
  @Override public int compareTo(Client client){
    return this.getCPF().compareTo(client.getCPF());
  }
}
