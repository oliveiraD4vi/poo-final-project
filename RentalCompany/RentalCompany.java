package RentalCompany;

import java.util.*;
import Date.Date;
import Persons.*;
import Tenancy.Tenancy;
import Vehicles.Car;
import Vehicles.Motorcycle;

//classe locadora
public class RentalCompany {
  private int id;
  private String address;
  private Manager manager;
  private List<Car> disponibleCarList = new ArrayList<Car>();
  private List<Motorcycle> disponibleMotorcycle = new ArrayList<Motorcycle>();
  private List<Tenancy> tenanciesList = new ArrayList<Tenancy>();
  private List<Client> clientsList = new ArrayList<Client>();

  /**O método Construtor recebe parâmetros para setar nos atributos
   * @param id é id da locadora
   * @param ManegerName é o gerente da locadora
   * @param address é o endereço da locadora 
   */
  public RentalCompany(int id, Manager manager, String address){
    if (id > 0){
      this.id = id;
      this.manager = manager;
      this.address = address;
    } else System.out.println("fail: invalid data");
  }

  /**
   * O metodo addCar adiciona um carro na locadora para aluguel
   * @param car é o carro a ser adicionado;
   */
  public void addCar(Car car){
    disponibleCarList.add(car);
    Collections.sort(disponibleCarList);
  }

  /**
   * O metodo removeCar remove um carro da locadora
   * @param car é o carro a ser removido
   * @return retorna um booleano, true caso o carro seja removido, false caso o carro não seja removido
   */
  public boolean removeCar(Car car){
    if (disponibleCarList.remove(car))
      return true;
    else {
      System.out.println("fail: car not found");
      return false;
    }
  }

  /**
   * O metodo addMotorcycle adiciona uma moto na locadora para aluguel
   * @param moto é a moto a ser adicionada
   */
  public void addMotorcycle(Motorcycle moto){
    disponibleMotorcycle.add(moto);
    Collections.sort(disponibleMotorcycle);
  }

  /**
   * O metodo removeMotorcycle remove uma moto da locadora
   * @param moto é a moto a ser removida
   * @return retorna true caso a moto seja rempvida e false caso não
   */
  public boolean removeMotorcycle(Motorcycle moto){
    if (disponibleMotorcycle.remove(moto))
      return true;
    else {
      System.out.println("fail: motorcycle not found");
      return false;
    }
  }

  /**
   * O metodo rentVehicle aluga carros e/ou motos
   * @param cars é uma lista dos carros a serem alugados
   * @param motos é uma lista de motos a serem alugados
   * @param cliente é o cliente que vai alugar os veiculos
   * @param dataAtual é a data do aluguel
   * @param dataEntrega é a data de entrega dos veiculos
   * @return retorna true caso ocorra o aluguel, false caso não
   */
  public boolean rentVehicle(List<Car> cars, List<Motorcycle> motos,  Client client, Date dataAtual, Date dataEntrega, int idRental){
    boolean verifyTenancies = false;
    
    for (Tenancy item : tenanciesList)
      if (item.getClient().getCPF().equals(client.getCPF()) && item.verifyStatus()) {
        verifyTenancies = true;
        break;
      }
    
    if (verifyTenancies) {
      System.out.print("\nfail: client already has an current tenancy contract");
      return false;
    } else {
      if (cars.size()+motos.size() == 1 || cars.size()+motos.size() == 2) {
        idRental++;
        
        if (cars.size() != 0) {
          for (Car carRent : cars) {
            for (Car carAvailable : disponibleCarList) {
              if (carRent.getId() == carAvailable.getId())
                carAvailable.setRented(true);
            }
          }
        }
        
        if (motos.size() != 0) {
          for (Motorcycle motoRent : motos) {
            for (Motorcycle motoAvailable : disponibleMotorcycle) {
              if (motoRent.getId() == motoAvailable.getId())
                motoAvailable.setRented(true);
            }
          }
        }

        Collections.sort(cars);
        Collections.sort(motos);
        
        Tenancy rent = new Tenancy(idRental, dataAtual, dataEntrega, client, cars, motos);
        tenanciesList.add(rent);

        Collections.sort(tenanciesList);

        boolean clientExist = false;

        for (Client item : clientsList)
          if (item.getCPF().equals(client.getCPF())) {
            clientExist = true;
            break;
          }

        if (!clientExist) {
          clientsList.add(client);
          Collections.sort(clientsList);
        }
        
        return true;
      } else {
        System.out.println("fail: vehicles quantity error");
        return false;
      }    
    }
  }   

  public void showCars(){
    if (disponibleCarList.size() == 0)
      System.out.println("No available cars");
    else
      for(int i = 0; i < disponibleCarList.size(); i++) System.out.println(disponibleCarList.get(i));
  }

  public void showMotorcycle(){
    if (disponibleMotorcycle.size() == 0)
      System.out.println("No available motorcycles");
    else
      for(int i = 0; i < disponibleMotorcycle.size(); i++) System.out.println(disponibleMotorcycle.get(i));
  }

  public void showTenancies(){
    System.out.println("\nRents:");
    if (tenanciesList.size() == 0)
      System.out.println("No registered rents");
    else
      for(int i = 0; i < tenanciesList.size(); i++) System.out.println(tenanciesList.get(i));
  }
  public void showClientsList(){
    System.out.println("\nClients:");
    if (tenanciesList.size() == 0)
      System.out.println("No registered clients");
    else
      for(int i = 0; i < clientsList.size(); i++) System.out.println(clientsList.get(i));
  }

  /**
   * Termina um aluguel de veiculo
   * @param tenancy é o aluguel a ser encerrado 
   * @param date é a data em que o contrado foi encerrado
   * @return retorna true caso contrato seja encerrado, false caso não
   */
  public boolean endTenancie(Tenancy tenancy, Date date){
    boolean test = tenanciesList.remove(tenancy);

    if (test) {
      tenanciesList.add(tenancy);
      tenancy.setStatus(false);

      if (tenancy.getCars().size() != 0)
        for (Car item : tenancy.getCars())
          if (item.verifyCondition() == true)
            item.setRented(false);

      if (tenancy.getMotorcycles().size() != 0)
        for (Motorcycle item : tenancy.getMotorcycles())
          if (item.verifyCondition() == true)
            item.setRented(false);

      float value = tenancy.calculateRentValue() + tenancy.calculateFine(date);
      System.out.printf("\nTotal rent value: R$ %.2f\n", value);
      return true;
    } else {
      System.out.println("fail: rent not found");
      return false;
    }
  }

  /**
   * Muda daata de vencimento do aluguel
   * @param tenancy é o aluguel que terá a data modificada
   * @param date é a nova data de vencimento
   * @return retorna a nova data
   */
  public Date changeDate(Tenancy tenancy, Date date){
    boolean teste = tenanciesList.remove(tenancy);

    if (teste) {
      tenancy.setDevolutionDate(date);
      tenanciesList.add(tenancy);
    }

    return date;
  }

  public int getId() {
    return id;
  }

  public List<Client> getClientsList() {
    return clientsList;
  }

  public List<Motorcycle> getDisponibleMotorcycle() {
    return disponibleMotorcycle;
  }

  public List<Car> getDisponibleCarList() {
    return disponibleCarList;
  }

  public List<Tenancy> getTenanciesList() {
    return tenanciesList;
  }

  public Manager getManager() {
    return manager;
  }

  public String getAddress() {
    return address;
  }

  public void setId(int id) {
    if(id >= 0) this.id = id;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setClientsList(ArrayList<Client> clientsList) {
    this.clientsList = clientsList;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  @Override
  public String toString() {
    String frase = "Manager: \n" + manager.getName();
    frase = frase + "Address: \n" + address;
    
    frase += "Carros: \n";
    if(disponibleCarList.size() == 0){
      frase += "Sem carros.\n";
    }else{
      for(int i = 0; i < disponibleCarList.size(); i++){
        frase += disponibleCarList.get(i);
        frase +="\n";
      }
    }

    frase += "Motos: \n";
    if(disponibleMotorcycle.size() == 0){
      frase += "Sem motos.\n";
    }else{
      for(int i = 0; i < disponibleMotorcycle.size(); i++){
        frase += disponibleMotorcycle.get(i);
        frase += "\n";
      }
    }

    frase += "Alocações: \n";
    if(tenanciesList.size() == 0){
      frase += "Sem alocações.\n";
    }else{
      for(int i = 0; i < tenanciesList.size(); i++){
        frase += tenanciesList.get(i);
        frase += "\n";
      }
    }
    
    frase += "Clientes: \n";
    if(tenanciesList.size() == 0){
      frase += "Sem Clientes.\n";
    }else{
      for(int i = 0; i < clientsList.size(); i++){
        frase += clientsList.get(i);
        frase += "\n";
      }
    }

    return frase;
  }
}
