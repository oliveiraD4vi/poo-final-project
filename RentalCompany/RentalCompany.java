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
    if (id >= 0){
      this.id = id;
      this.manager = manager;
      this.address = address;
    } else System.out.println("fail: dados invalidos");
  }

  public void addCar(Car car){
    disponibleCarList.add(car);
  }

  public boolean removeCar(Car car){
    boolean test = disponibleCarList.remove(car);
    
    if (test == false)
      System.out.println("fail: carro não presente");
    
    return test;
  }

  public void addMotorcycle(Motorcycle moto){
    disponibleMotorcycle.add(moto);
  }

  public boolean removeMotorcycle(Motorcycle moto){
    boolean test = disponibleMotorcycle.remove(moto);
    
    if (test == false)
      System.out.println("fail: moto não presente");

    return test;
  }

  // alterar essa classe
  public boolean rentVehicle(List<Car> cars, List<Motorcycle> motos,  Client cliente, Date dataAtual, Date dataEntrega){
    ArrayList<Car> carros = new ArrayList<Car>();
    ArrayList<Motorcycle> motosDis = new ArrayList<Motorcycle>();
    boolean testeGeral = false;

    switch (cars.size()+motos.size()) {
      case 1:
        boolean teste = false;

        if (cars.size() != 0) {
          Car car = cars.get(0);
          if (car != null) teste = removeCar(car);
          
          if (teste == true) {
            carros.add(car);
            testeGeral = true;
          }
        }
        
        if (motos.size() != 0) {
          teste = false;
          Motorcycle moto = motos.get(0);
          
          if (moto != null) teste = removeMotorcycle(moto);
          
          if (teste == true) {
            motosDis.add(moto);
            testeGeral = true;
          }
        }

        if(testeGeral == false) return false;

        Tenancy aux = new Tenancy(idRental, dataAtual, dataEntrega, cliente, carros, motosDis);
        idRental++;
        tenanciesList.add(aux);
        clientsList.add(cliente);
        return true;

        case 2:
        for(int i = 0; i < 2; i++){
          boolean teste2 = false;

          if (cars.size() != 0) {
            Car car = cars.get(i);
            if (car != null) teste = removeCar(car);
            
            if (teste2 == true) {
              carros.add(car);
              testeGeral = true;
            }
          }
          
          if (motos.size() != 0) {
            teste2 = false;
            Motorcycle moto = motos.get(i);
            
            if (moto != null) teste = removeMotorcycle(moto);
            
            if (teste2 == true) {
              motosDis.add(moto);
              testeGeral = true;
            }
          }
        }

        if(testeGeral == false) return false;

        Tenancy aux2 = new Tenancy(idRental, dataAtual, dataEntrega, cliente, carros, motosDis);
        idRental++;
        tenanciesList.add(aux2);
        clientsList.add(cliente);
        return true;
      default:
        System.out.println("Locação inválida: quantidade excessiva de véiculos");
        return false;
    }
  }   

  public void showCars(){
    System.out.println("\nCarros:");
    if (disponibleCarList.size() == 0) System.out.println("Não há carros disponíveis");
    else {
      for(int i = 0; i < disponibleCarList.size(); i++) System.out.println(disponibleCarList.get(i));
    }
  }

  public void showMotorcycle(){
    System.out.println("\nMotos:");
    if (disponibleMotorcycle.size() == 0) System.out.println("Não há motos disponíveis");
    else {
      for(int i = 0; i < disponibleMotorcycle.size(); i++) System.out.println(disponibleMotorcycle.get(i));
    }
  }

  public void showTenancies(){
    System.out.println("\nLocações:");
    if (tenanciesList.size() == 0) System.out.println("Não há locações registradas");
    else {
      for(int i = 0; i < tenanciesList.size(); i++) System.out.println(tenanciesList.get(i));
    }
  }
  public void showClientsList(){
    System.out.println("\nClientes:");
    if (tenanciesList.size() == 0) System.out.println("Não há clientes registrados");
    else {
      for(int i = 0; i < clientsList.size(); i++) System.out.println(clientsList.get(i));
    }
  }

  public boolean endTenancie(Tenancy tenancy, Date date){
    boolean teste = tenanciesList.remove(tenancy);

    if (teste) {
      float multa = tenancy.calculateFine(date);
      float valor = tenancy.calculateRentValue();
      valor += multa;
      System.out.printf("Total a pagar pelo aluguel: $%f.2", valor);
      return true;
    } else {
      System.out.println("Locação não encontrada");
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
