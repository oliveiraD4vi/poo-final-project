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

  Tenancy(int id, Date rentDate, Date devolutionDate, Client client, List<Car> cars, List<Motorcycle> motorcycles) {
    this.id = id;
    this.rentDate = rentDate;
    this.devolutionDate = devolutionDate;
    this.client = client;
    this.cars = cars;
    this.motorcycles = motorcycles;
    this.status = true;
  }

  public float calculateFine() {
    return 0;
  }

  public float calculateRentValue() {
    return 0;
  }

  public void setStatus() {

  }

  public boolean verifyStatus() {
    return status;
  }

  public int getId() {
    return id;
  }

  public Date getRentDate() {
    return rentDate;
  }

  public Date getDevolutionDate() {
    return devolutionDate;
  }

  public Client getClient() {
    return client;
  }

  public String toString() {
    return (cars + " " + motorcycles);
  }
}
