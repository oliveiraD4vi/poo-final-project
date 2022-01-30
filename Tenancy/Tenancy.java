/**
 * A classe Tenancy representa o registro, bem como o contrato de aluguel
 * de veículos efetuado entre o cliente e a locadora e possui atributos tais
 * como id, data de aluguel, data de devolução, status (que pode ser true,
 * para um contrato aberto, ou false, para um contrato finalizado), as listas
 * de carros e/ou motos alugadas e as informações do cliente.
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 28/01/2022
 */

package Tenancy;

import Date.Date;
import Vehicles.*;
import Persons.Client;
import java.util.List;
import java.util.ArrayList;

public class Tenancy {
  private int id;
  private Date rentDate;
  private Date devolutionDate;
  private Client client;
  private boolean status;
  private List<Car> cars = new ArrayList<Car>();
  private List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();

  /**
   * O método Construtor recebe parâmetros para setar nos atributos
   * base da classe
   * @param id é o id do contrato
   * @param rentDate é a data do aluguel
   * @param devolutionDate é a data de devolução
   * @param client é o objeto que guarda as informações do cliente
   * @param cars é a lista de carros
   * @param motorcycles é a lista de motos
   */
  public Tenancy(int id, Date rentDate, Date devolutionDate, Client client, List<Car> cars, List<Motorcycle> motorcycles) {
    this.id = id;
    this.rentDate = rentDate;
    this.devolutionDate = devolutionDate;
    this.client = client;
    this.cars = cars;
    this.motorcycles = motorcycles;
    setStatus(true);
  }

  /**
   * O método calculateFine calcula a multa, caso seja necessário,
   * considerando os dias que se passaram da data original
   * @param date é a data da devolução final
   * @return o valor float da multa a ser paga
   */
  public float calculateFine(Date date) {
    float fine = 0;
    int days = calculateDays(devolutionDate, date);

    if (cars.size() != 0)
      for (int i = 0; i<cars.size(); i++)
        fine =+ cars.get(i).calculatePriceByDay(rentDate.getMonth())*(float)0.1*days;

    if (motorcycles.size() != 0)
      for (int i = 0; i<motorcycles.size(); i++)
        fine =+ motorcycles.get(i).calculatePriceByDay(rentDate.getMonth())*(float)0.1*days;

    return fine;
  }

  /**
   * O método calculateRentValue calcula o valor total do aluguel
   * dos veículos
   * @return o valor total do aluguel nos dias do contrato
   */
  public float calculateRentValue() {
    float rentValue = 0;
    int days = calculateDays(rentDate, devolutionDate);

    if (cars.size() != 0)
      for (int i = 0; i<cars.size(); i++)
        rentValue =+ cars.get(i).calculatePriceByDay(rentDate.getMonth())*days;

    if (motorcycles.size() != 0)
      for (int i = 0; i<motorcycles.size(); i++)
        rentValue =+ motorcycles.get(i).calculatePriceByDay(rentDate.getMonth())*days;

    return rentValue;
  }

  /**
   * O método calculateDays calcula a quantidade de dias entre duas datas
   * @param d1 é a menor data
   * @param d2 é a maior data
   * @return quantidade de dias entre as datas
   */
  private int calculateDays(Date d1, Date d2) {
    int days = 10;

    if (d1.getMonth() == d2.getMonth()) {
      days = d2.getDay() - d1.getDay();
    }

    return days;
  }

  /**
   * O método setStatus seta o valor do status do contrato de locação
   * @param value é o valor a ser setado no contrato
   */
  public void setStatus(boolean value) {
    this.status = value;
  }

  public void setDevolutionDate(Date devolutionDate) {
      this.devolutionDate = devolutionDate;
  }

  /**
   * O método verifyStatus retorna true se o contrato ainda
   * estiver em aberto e false se estiver concluído
   * @return status
   */
  public boolean verifyStatus() {
    return status;
  }

  /**
   * O método getID retorna o ID do contrato de locação
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * O método getRentDate retorna o objeto Date com 
   * a data de alguel dos veículos
   * @return rentDate
   */
  public Date getRentDate() {
    return rentDate;
  }

  /**
   * O método getDevolutionDate retorna o objeto Date
   * com a data prevista da devolução dos alugueis
   * @return devolutionDate
   */
  public Date getDevolutionDate() {
    return devolutionDate;
  }

  /**
   * O método getClient retorna o objeto com as informações do cliente
   * @return client
   */
  public Client getClient() {
    return client;
  }

  /**
   * O método toString retorna a classe em formato de string
   */
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append("\nLocation Contract | " + getId());
    stringBuffer.append("\nClient informations:\n " + getClient());
    stringBuffer.append("\n\nRent date: " + getRentDate());
    stringBuffer.append("\nDevolution date: " + getDevolutionDate());
    stringBuffer.append("\n\nRented vehicles:\n\n");

    if (cars.size() != 0)
      for (int i = 0; i<cars.size(); i++)
        stringBuffer.append(cars.get(i) + "\n");

    if (motorcycles.size() != 0)
      for (int i = 0; i<motorcycles.size(); i++)
        stringBuffer.append(motorcycles.get(i) + "\n");

    return stringBuffer.toString();
  }
}
