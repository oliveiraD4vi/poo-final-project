/**
 * A classe abstrata vahicle representa um determinado veículo
 * genérico que pode ser um carro ou uma moto. Os atributos presentes
 * nesta classe incluem id, tipo (type), placa (plate), marca (brand),
 * modelo (model), cor (color) e preço base (basePrice), que é o preço
 * base de locação diária do veículo (este valor será alterado dependendo)
 * do tipo de veículo. O atributo alugado (rented) determina se o
 * veículo se encontra disponível ou não.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 25/01/2022
 */

package Vehicles;

abstract public class Vehicle {
  protected int id;
  protected char type; //C or M
  protected String plate;
  protected String brand;
  protected String model;
  protected String color;
  protected boolean rented = false;
  protected float basePrice;

  /**
   * O método Construtor da classe recebe os parâmetros referentes
   * aos atributos especificado e seta os valores iniciais
   * @param id é o id do veículo para uso interno
   * @param brand é a marca do veículo
   * @param model é o modelo do veículo
   * @param color é a cor do veículo
   * @param plate é a placa do veículo
   */
  Vehicle (int id, String brand, String model, String color, String plate) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.plate = plate;
  }
  
  /**
   * O método abstrato calculatePriceByDay retorna o valor
   * total do aluguel do veículo por dia dado o mês, considerando
   * se esse mês é de alta ou não
   * @param month é o mês em que o aluguel está sendo feito
   * @return valor do aluguel por dia
   */
  abstract public float calculatePriceByDay(int month);

  /**
   * O método verifyConfition verifica se o veículo está alugado
   * ou não
   * @return rented
   */
  public boolean verifyCondition() {
    return this.rented;
  }

  /**
   * O método setBasePrice seta o valor base do preço
   * de aluguel por dia do véiculo. Este valor muda
   * dependendo do tipo de veículo
   * @param basePrice é o valor a ser setado
   */
  public void setBasePrice(float basePrice) {
    this.basePrice = basePrice;
  }

  /**
   * O método setType seta o tipo de veículo
   * @param type é o tipo a ser setado (C ou M)
   */
  public void setType(char type) {
    this.type = type;
  }

  /**
   * O método setRented seta o atributo que diz se o
   * veículo está alugado ou não
   * @param rented o valor a ser setado
   */
  public void setRented(boolean rented) {
    this.rented = rented;
  }

  /**
   * O método getId retorna o id do veículo
   * @return id
   */
  public int getId() {
    return this.id;
  }

  /**
   * O método getType retorna o tipo do veículo
   * @return type
   */
  public char getType() {
    return this.type;
  }

  /**
   * O método getBrand retorna a marca do veículo
   * @return brand
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * O método getModel retorna o modelo do veículo
   * @return model
   */
  public String getModel() {
    return this.model;
  }

  /**
   * O método getColor retorna a cor do veículo
   * @return color
   */
  public String getColor() {
    return this.color;
  }

  /**
   * O método getPlate retorna a placa do veículo
   * @return plate
   */
  public String getPlate() {
    return this.plate;
  }

  /**
   * O método toString retorna a classe em um formato
   * de string pré definido
   */
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("ID: " + getId() + " | " + getPlate().toUpperCase() + "\n");
    stringBuffer.append(getBrand() + " - " + getModel() + ", " + getColor() + "\n");

    return stringBuffer.toString();
  }
}
