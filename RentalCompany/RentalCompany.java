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

}
