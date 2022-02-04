/**
 * A classe Car que estende a classe Vehicle representa um carro
 * que possui os atributos definidos em vehicle e seta o atributo tipo
 * e o atributo basePrice. A classe implementa o método calculatePriceByDay
 * que utiliza o valor do atributo basePrice para calcular o valor total do
 * aluguel diário do veículo, no caso da moto, retorna o preço base * 10%
 * nos meses corridos e * 15% nos meses de alta.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 25/01/2022
 */

package Vehicles;

public class Car extends Vehicle implements Comparable<Car> {
  /**
   * O método Construtor da classe seta os valores iniciais do
   * objeto e os valores não atribuídos inicialmente na classe
   * abstrata Vehicle (type e basePrice)
   * @param id é o id do carro
   * @param brand é a marca do carro
   * @param model é o modelo do carro
   * @param color é a cor do carro
   * @param plate é a placa do carro
   */
  public Car (int id, String brand, String model, String color, String plate) {
    super(id, brand, model, color, plate);
    setType("C");
    setBasePrice((float)18.5);
  }

  @Override
  public float calculatePriceByDay(int month) {
    float priceByDay = 0;
    int[] highMonth = {1, 2, 5, 6, 11, 12};

    for (int i = 0; i<highMonth.length; i++) {
      if (month == highMonth[i]) {
        priceByDay = (float)(basePrice + basePrice * 0.15);
      } else {
        priceByDay = (float)(basePrice + basePrice * 0.10);
      }
    }

    return priceByDay;
  }

  @Override public int compareTo(Car c){
    return Integer.compare(this.id, c.getId());
  }
}
