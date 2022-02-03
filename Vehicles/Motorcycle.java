/**
 * A classe Motorcycle que estende a classe Vehicle representa uma moto
 * que possui os atributos definidos em vehicle e seta o atributo tipo
 * e o atributo basePrice. A classe implementa o método calculatePriceByDay
 * que utiliza o valor do atributo basePrice para calcular o valor total do
 * aluguel diário do veículo, no caso da moto, retorna o preço base * 5%
 * nos meses corridos e * 10% nos meses de alta.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 25/01/2022
 */

package Vehicles;

public class Motorcycle extends Vehicle implements Comparable<Motorcycle> {
  /**
   * O método Construtor da classe seta os valores iniciais do
   * objeto e os valores não atribuídos inicialmente na classe
   * abstrata Vehicle (type e basePrice)
   * @param id é o id da moto
   * @param brand é a marca da moto
   * @param model é o modelo da moto
   * @param color é a cor da moto
   * @param plate é a placa da moto
   */
  public Motorcycle (int id, String brand, String model, String color, String plate) {
    super(id, brand, model, color, plate);
    setType('M');
    setBasePrice((float)12.5);
  }

  @Override
  public float calculatePriceByDay(int month) {
    float priceByDay = 0;
    int[] highMonth = {1, 2, 5, 6, 11, 12};

    for (int i = 0; i<highMonth.length; i++) {
      if (month == highMonth[i]) {
        priceByDay = (float)(basePrice + basePrice * 0.10);
      } else {
        priceByDay = (float)(basePrice + basePrice * 0.05);
      }
    }

    return priceByDay;
  }

  @Override public int compareTo(Motorcycle m){
    return Integer.compare(this.id, m.getId());
  }
}
