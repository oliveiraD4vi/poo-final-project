/**
 * A classe Brand é um enum para valores de marcas determinadas
 * as quais podem ser atribuídas aos veículos desse sistema.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 24/04/2022
 */

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

  /**
   * O método construtor Brand recebe como parâmetro
   * o atributo name, que será o valor atribuído ao objeto
   * dentro dos valores pré-establecidos.
   * @param name é o nome atribuído ao objeto
   */
  private Brand(String name) {
    this.name = name;
  }

  /**
   * O método getName retorna o nome atribuído
   * ao objeto.
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * O método toString retorna a classe
   * em formato de String. No caso de um enum,
   * ele retorna o nome atribuído ao objeto. 
   */
  public String toString() {
    return getName();
  }
}
