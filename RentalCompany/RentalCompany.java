package RentalCompany;

import java.util.ArrayList;

import Date.Date;
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
    private int idRetal;
    private String address;

    public RentalCompany(int id, String manegerName, String address){
        if(id >= 0){
            this.id = id;
            this.managerName = manegerName;
            this.address = address;
        }else{
            System.out.println("fail: dados invalidos");
        }
    }

    public void addCars(Car car){
        disponibleCarList.add(car);
    }

    public boolean removeCar(Car car){
        boolean test = disponibleCarList.remove(car);
        if(test == false){
            System.out.println("fail: carro não presente");
        }
        return test;
    }

    public void addMotorcycle(Motorcycle moto){
        disponiblMotorcycle.add(moto);
    }

    public boolean removeMotorcycle(Motorcycle moto){
        boolean test = disponiblMotorcycle.remove(moto);
        if(test == false){
            System.out.println("fail: moto não presente");
        }
        return test;
    }

    public boolean rentVehicle(ArrayList<Car> cars, ArrayList<Motorcycle> motos,  Client cliente, Date dataAtual, Date dataEntrega){
        ArrayList<Car> carros = new ArrayList<Car>();
        ArrayList<Motorcycle> motosDis = new ArrayList<Motorcycle>();
        boolean testeGeral = false;
        if((cars.size()+motos.size()) > 2){
            return false;
        }else{
            for(int i = 0; i < 2; i++){
                Car car = cars.get(i);
                Motorcycle moto = motos.get(i);
                boolean teste = false;
                if(car != null){
                    teste = removeCar(car);
                }
                if(teste == true){
                    carros.add(car);
                    testeGeral = true;
                }
                teste = false;
                if(moto != null){
                    teste = removeMotorcycle(moto);
                }
                if(teste == true){
                    motosDis.add(moto);
                    testeGeral = true;
                }
            }
            if(testeGeral == false){
                return false;
            }
            Tenancy aux = new Tenancy(idRetal, dataAtual, dataEntrega, cliente, carros, motosDis);
            idRetal++;
            tenanciesList.add(aux);
            clientsList.add(cliente);
            return true;
        } 
    }   

    public void showCars(){
        System.out.println("Carros: ");
        if(disponibleCarList.size() == 0){
            System.out.println("Sem carros.");
        }else{
            for(int i = 0; i < disponibleCarList.size(); i++){
                System.out.println(disponibleCarList.get(i));
                System.out.println("==============================");
            }
        }
    }
    public void showMotorcycle(){
        System.out.println("Motos: ");
        if(disponiblMotorcycle.size() == 0){
            System.out.println("Sem motos.");
        }else{
            for(int i = 0; i < disponiblMotorcycle.size(); i++){
                System.out.println(disponiblMotorcycle.get(i));
                System.out.println("==============================");
            }
        }
    }

    public void showTenancies(){
        System.out.println("Alocações: ");
        if(tenanciesList.size() == 0){
            System.out.println("Sem alocações.");
        }else{
            for(int i = 0; i < tenanciesList.size(); i++){
                System.out.println(tenanciesList.get(i));
                System.out.println("==============================");
            }
        }
    }
    public void showClientsList(){
        System.out.println("Clientes: ");
        if(tenanciesList.size() == 0){
            System.out.println("Sem Clientes.");
        }else{
            for(int i = 0; i < clientsList.size(); i++){
                System.out.println(clientsList.get(i));
                System.out.println("==============================");
            }
        }
    }

    public boolean endTenancie(Tenancy tenancy, Date date){
        boolean teste = tenanciesList.remove(tenancy);
        if(teste){
            float multa = tenancy.calculateFine(date);
            float valor = tenancy.calculateRentValue();
            valor += multa;
            System.out.printf("Total a pagar pelo aluguel $%f.2", valor);
            return true;
        }else{
            System.out.println("Alocação não encontrada");
            return false;
        }
    }

    public Date changeDate(Tenancy tenancy, Date date){
        boolean teste = tenanciesList.remove(tenancy);
        if(teste){
            tenancy.setDevolutionDate(date);
            tenanciesList.add(tenancy);
        }
        return date;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Client> getClientsList() {
        return clientsList;
    }

    public ArrayList<Motorcycle> getDisponiblMotorcycle() {
        return disponiblMotorcycle;
    }

    public ArrayList<Car> getDisponibleCarList() {
        return disponibleCarList;
    }

    public String getManagerName() {
        return managerName;
    }

    public ArrayList<Tenancy> getTenanciesList() {
        return tenanciesList;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        if(id >= 0){
            this.id = id;
        }
    }

    public void setClientsList(ArrayList<Client> clientsList) {
        this.clientsList = clientsList;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
