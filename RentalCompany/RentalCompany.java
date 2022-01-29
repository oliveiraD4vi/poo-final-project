package RentalCompany;

import java.util.ArrayList;

import Persons.Client;
import Tenancy.Tenancy;
import Vehicles.Car;
import Vehicles.Motorcycle;

//classe locadora
public class RentalCompany {
    private int id;
    private String managerName;
    private ArrayList<Car> disponibleCarList = new ArrayList<Car>();
    private ArrayList<Motorcycle> disponiblMotorcycle = new ArrayList<Motorcycle>();
    private ArrayList<Tenancy> tenanciesList = new ArrayList<Tenancy>();
    private ArrayList<Client> clientsList = new ArrayList<Client>();

    public RentalCompany(int id, String manegerName){
        if(id >= 0){
            this.id = id;
            this.managerName = manegerName;
        }else{
            System.out.println("fail: dados invalidos");
        }
    }

    public void addCars(Car car){
        disponibleCarList.add(car);
    }

    public void removeCar(Car car){
        boolean test = disponibleCarList.remove(car);
        if(test == false){
            System.out.println("fail: carro não presente");
        }

    }

    public void addMotorcycle(Motorcycle moto){
        disponiblMotorcycle.add(moto);
    }

    public void removeMotorcycle(Motorcycle moto){
        boolean test = disponiblMotorcycle.remove(moto);
        if(test == false){
            System.out.println("fail: moto não presente");
        }
    }
}
