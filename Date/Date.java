/**
 * A classe Date representa uma determinada data que, por sua
 * vez, possui dia (day), mês (month) e ano (year), cujos valores
 * são representados por atributos de mesmo nome. Existe também
 * um atributo chamado daysByMonth que configura um array com a
 * quantidade de dias por mês, dada a posição do valor no array
 * como o mês. Quanto aos métodos, a classe apresenta o construtor,
 * o toString e os getters para os atributos day, month e year.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 25/01/2022
 */

package Date;

public class Date {
  private byte day;
  private byte month;
  private short year;
  private static final int[] daysByMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  /**
   * O método Construtor da classe Date recebe os parâmetros
   * day, month e year para serem passados aos atributos de
   * mesmo nome da classe.
   * @param day é o valor a ser passado para o atributo day
   * @param month é o valor a ser passado para o atributo month
   * @param year é o valor a ser passado para o atributo year
   */
  public Date(byte day, byte month, short year) {
    if(month >= 1 && month <= 12) {
      if(month == 2 && day == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
        this.day = day;
        this.month = month;
        this.year = year;
      } else {
        if(day >= 0 && day <= daysByMonth[month]) {
          this.day = day;
          this.month = month;
          this.year = year;
        }
      }
    }
  }

  /**
   * O método getDay retorna o valor do atributo day
   * @return day
   */
  public byte getDay() {
    return day;
  }

  /**
   * O método getMonth retorna o valor do atributo month
   * @return month
   */
  public byte getMonth() {
    return month;
  }

  /**
   * O método getYear retorna o valor do atributo year
   * @return year
   */
  public short getYear() {
    return year;
  }

  /**
   * O método toString retorna a classe em um formato de
   * string pré determinado
   */
  public String toString() {
    return String.format("%02d/%02d/%04d", day, month, year);
  }
}
