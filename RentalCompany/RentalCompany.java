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
  private int idRental;
  private String address;
  private Manager manager;
  private List<Car> disponibleCarList = new ArrayList<Car>();
  private List<Motorcycle> disponibleMotorcycle = new ArrayList<Motorcycle>();
  private List<Tenancy> tenanciesList = new ArrayList<Tenancy>();
  private List<Client> clientsList = new ArrayList<Client>();

  public RentalCompany(int id, Manager manager, String address){
    if (id > 0){
      this.id = id;
      this.manager = manager;
      this.address = address;
    } else System.out.println("fail: invalid data");
  }

  public void addCar(Car car){
    disponibleCarList.add(car);
  }

  public boolean removeCar(Car car){
    if (disponibleCarList.remove(car))
      return true;
    else {
      System.out.println("fail: car not found");
      return false;
    }
  }

  public void addMotorcycle(Motorcycle moto){
    disponibleMotorcycle.add(moto);
  }

  public boolean removeMotorcycle(Motorcycle moto){
    if (disponibleMotorcycle.remove(moto))
      return true;
    else {
      System.out.println("fail: motorcycle not found");
      return false;
    }
  }

  public boolean rentVehicle(List<Car> cars, List<Motorcycle> motos,  Client client, Date dataAtual, Date dataEntrega){
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
        
        Tenancy rent = new Tenancy(idRental, dataAtual, dataEntrega, client, cars, motos);
        tenanciesList.add(rent);

        boolean clientExist = false;

        for (Client item : clientsList)
          if (item.getCPF().equals(client.getCPF())) {
            clientExist = true;
            break;
          }

        if (!clientExist) clientsList.add(client);
        
        return true;
      } else {
        System.out.println("fail: vehicles quantity error");
        return false;
      }    
    }
  }   

  public void showCars(){
    System.out.println("\nCars:");
    if (disponibleCarList.size() == 0) System.out.println("No available cars");
    else {
      for(int i = 0; i < disponibleCarList.size(); i++) System.out.println(disponibleCarList.get(i));
    }
  }

  public void showMotorcycle(){
    System.out.println("\nMotorcycles:");
    if (disponibleMotorcycle.size() == 0) System.out.println("No available motorcycles");
    else {
      for(int i = 0; i < disponibleMotorcycle.size(); i++) System.out.println(disponibleMotorcycle.get(i));
    }
  }

  public void showTenancies(){
    System.out.println("\nRents:");
    if (tenanciesList.size() == 0) System.out.println("No registered rents");
    else {
      for(int i = 0; i < tenanciesList.size(); i++) System.out.println(tenanciesList.get(i));
    }
  }
  public void showClientsList(){
    System.out.println("\nClients:");
    if (tenanciesList.size() == 0) System.out.println("No registered clients");
    else {
      for(int i = 0; i < clientsList.size(); i++) System.out.println(clientsList.get(i));
    }
  }

  public boolean endTenancie(Tenancy tenancy, Date date){
    boolean test = tenanciesList.remove(tenancy);

    if (test) {
      tenanciesList.add(tenancy);
      tenancy.setStatus(false);
      float multa = tenancy.calculateFine(date);
      float valor = tenancy.calculateRentValue();
      valor += multa;
      System.out.printf("Total rent value: $%f.2", valor);
      return true;
    } else {
      System.out.println("Rent not found");
      return false;
    }
  }

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

  public Manager getManager() {
    return manager;
  }

  public List<Tenancy> getTenanciesList() {
    return tenanciesList;
  }

  public String getAddress() {
    return address;
  }

  public void setId(int id) {
    if(id >= 0) this.id = id;
  }

  public void setClientsList(ArrayList<Client> clientsList) {
    this.clientsList = clientsList;
  }
}
