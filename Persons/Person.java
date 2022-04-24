/**
 * A classe abstrata Person representa uma determinada pessoa/
 * usuário do sistema, instanciando atributos básicos que todas
 * as pessoas devem ter, tais como CPF, nome, email e telefone.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 24/04/2022
 */

package Persons;

public class Person {
  private String name;
  private String CPF;
  private String email;
  private String phone;

  /**
   * O método construtor Person recebe os parâmetros name, CPF, email e
   * phone e seta os respectivos atributos do objeto.
   * @param name é o nome da pessoa
   * @param CPF é o CPF da pessoa
   * @param email é o email da pessoa
   * @param phone é o telefone da pessoa
   */
  public Person(String name, String CPF, String email, String phone) {
    this.name = name;
    this.CPF = CPF;
    this.email = email;
    this.phone = phone;
  }

  /**
   * O método getName retorna o nome da pessoa
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * O método getCPF retorna o CPF da pessoa
   * @return CPF
   */
  public String getCPF() {
    return CPF;
  }

  /**
   * O método getEmail retorna o email da pessoa
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * O método getPhone retorna o telefone da pessoa
   * @return phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * O método toString retorna a classe Person
   * em forma de string
   */
  public String toString() {
    return (
      getName() + " | "
      + getCPF() + " | "
      + getEmail() + " | "
      + getPhone()
    );
  }
}
