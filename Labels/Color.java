/**
 * A classe Color é um enum para valores de cores determinadas
 * as quais podem ser atribuídas aos veículos desse sistema.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 24/04/2022
 */

package Labels;

public enum Color {
  black("Black"),
  blue("Blue"),
  red("Red"),
  white("White"),
  silver("Silver");

  private String name;

  /**
   * O método construtor Color recebe como parâmetro
   * o atributo name, que será o valor atribuído ao objeto
   * dentro dos valores pré-establecidos.
   * @param name é o nome atribuído ao objeto
   */
  private Color(String name) {
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
