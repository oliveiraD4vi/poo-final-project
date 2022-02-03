package RentalCompany;

import java.util.ArrayList;
import java.util.Collections;
import Date.Date;
import Persons.Client;
import Persons.Manager;
import Tenancy.Tenancy;
import Vehicles.Car;
import Vehicles.Motorcycle;

//classe locadora
public class RentalCompany {
    private int id;
    private Manager managerName;
    private ArrayList<Car> disponibleCarList = new ArrayList<Car>();
    private ArrayList<Motorcycle> disponiblMotorcycle = new ArrayList<Motorcycle>();
    private ArrayList<Tenancy> tenanciesList = new ArrayList<Tenancy>();
    private ArrayList<Client> clientsList = new ArrayList<Client>();
    private int idRetal;
    private String address;

    /**O método Construtor recebe parâmetros para setar nos atributos
     * @param id é id da locadora
     * @param ManegerName é o gerente da locadora
     * @param address é o endereço da locadora 
     */
    public RentalCompany(int id, Manager manegerName, String address){
        if(id >= 0){
            this.id = id;
            this.managerName = manegerName;
            this.address = address;
        }else{
            System.out.println("fail: dados invalidos");
        }
    }

    /**
     * O metodo addCars adiciona um carro na locadora para aluguel
     * @param car é o carro a ser adicionado;
     */
    public void addCars(Car car){
        disponibleCarList.add(car);
        Collections.sort(disponibleCarList);
    }
    /**
     * O metodo removeCar remove um carro da locadora
     * @param car é o carro a ser removido
     * @return retorna um booleano, true caso o carro seja removido, false caso o carro não seja removido
     */
    public boolean removeCar(Car car){
        boolean test = disponibleCarList.remove(car);
        if(test == false){
            System.out.println("fail: carro não presente");
        }
        return test;
    }

    /**
     * O metodo addMotorcycle adiciona uma moto na locadora para aluguel
     * @param moto é a moto a ser adicionada
     */
    public void addMotorcycle(Motorcycle moto){
        disponiblMotorcycle.add(moto);
        Collections.sort(disponiblMotorcycle);
    }

    /**
     * O metodo removeMotorcycle remove uma moto da locadora
     * @param moto é a moto a ser removida
     * @return retorna true caso a moto seja rempvida e false caso não
     */
    public boolean removeMotorcycle(Motorcycle moto){
        boolean test = disponiblMotorcycle.remove(moto);
        if(test == false){
            System.out.println("fail: moto não presente");
        }
        return test;
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
    public boolean rentVehicle(ArrayList<Car> cars, ArrayList<Motorcycle> motos,  Client cliente, Date dataAtual, Date dataEntrega){
        ArrayList<Car> carros = new ArrayList<Car>();
        ArrayList<Motorcycle> motosDis = new ArrayList<Motorcycle>();
        boolean testeGeral = false;
        int num = 0;
        if(cars != null){
            num += cars.size();
        }
        if(motos != null){
            num += motos.size();
        }

        if(num > 2){
            return false;
        }else{
            Car car = null;
            Motorcycle moto = null;
            for(int i = 0; i < 2; i++){
                if(cars != null){
                    car = cars.get(i);
                }
                
                if(motos != null){
                    moto = motos.get(i);
                }
                
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
                if(cars != null ){
                    if(cars.size() == 1){
                        i = 2;
                    }
                }
                if(motos != null){
                    if(motos.size() == 1){
                        i = 2;
                    }
                }
            }
            if(testeGeral == false){
                return false;
            }
            Collections.sort(carros);
            Collections.sort(motosDis);
            Tenancy aux = new Tenancy(idRetal, dataAtual, dataEntrega, cliente, carros, motosDis);
            idRetal++;
            tenanciesList.add(aux);
            clientsList.add(cliente);
            Collections.sort(tenanciesList);
            Collections.sort(clientsList);
            return true;
        } 
    }   

    /**
     * Mostra os carros disponiveis para aluguel
     */
    public void showCars(){
        System.out.println("Carros: ");
        if(disponibleCarList.size() == 0){
            System.out.println("Sem carros.");
            System.out.println("==============================");
        }else{
            for(int i = 0; i < disponibleCarList.size(); i++){
                System.out.println(disponibleCarList.get(i));
                System.out.println("==============================");
            }
        }
    }
    /**
     * Mostra as motos disponiveis para aluguel
     */
    public void showMotorcycle(){
        System.out.println("Motos: ");
        if(disponiblMotorcycle.size() == 0){
            System.out.println("Sem motos.");
            System.out.println("==============================");
        }else{
            for(int i = 0; i < disponiblMotorcycle.size(); i++){
                System.out.println(disponiblMotorcycle.get(i));
                System.out.println("==============================");
            }
        }
    }

    /**
     * Mostra os alugueis realizados
     */
    public void showTenancies(){
        System.out.println("Alocações: ");
        if(tenanciesList.size() == 0){
            System.out.println("Sem alocações.");
            System.out.println("==============================");
        }else{
            for(int i = 0; i < tenanciesList.size(); i++){
                System.out.println(tenanciesList.get(i));
                System.out.println("==============================");
            }
        }
    }

    /**
     * Mostra os clientes da locadora de carro
     */
    public void showClientsList(){
        System.out.println("Clientes: ");
        if(tenanciesList.size() == 0){
            System.out.println("Sem Clientes.");
            System.out.println("==============================");
        }else{
            for(int i = 0; i < clientsList.size(); i++){
                System.out.println(clientsList.get(i));
                System.out.println("==============================");
            }
        }
    }

    /**
     * Termina um aluguel de veiculo
     * @param tenancy é o aluguel a ser encerrado 
     * @param date é a data em que o contrado foi encerrado
     * @return retorna true caso contrato seja encerrado, false caso não
     */
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

    /**
     * Muda daata de vencimento do aluguel
     * @param tenancy é o aluguel que terá a data modificada
     * @param date é a nova data de vencimento
     * @return retorna a nova data
     */
    public Date changeDate(Tenancy tenancy, Date date){
        boolean teste = tenanciesList.remove(tenancy);
        if(teste){
            tenancy.setDevolutionDate(date);
            tenanciesList.add(tenancy);
        }
        return date;
    }

    //Gets e Sets
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

    public Manager getManagerName() {
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

    public void setManagerName(Manager managerName) {
        this.managerName = managerName;
    }

    //O metodo toString retorna uma String formatada com as informações do objeto da Classe RentalCompany
    @Override
    public String toString() {
        String frase = "Maneger: \n" + managerName.getName() + "\n==============================\n";
        frase = frase + "Address: \n" + address + "\n==============================\n";
        
        frase += "Carros: \n";
        if(disponibleCarList.size() == 0){
            frase += "Sem carros.\n";
            frase += "\n==============================\n";
        }else{
            for(int i = 0; i < disponibleCarList.size(); i++){
                frase += disponibleCarList.get(i);
                frase +="\n";
            }
            frase +="\n==============================\n";
        }

        frase += "Motos: \n";
        if(disponiblMotorcycle.size() == 0){
            frase += "Sem motos.\n";
            frase += "\n==============================\n";
        }else{
            for(int i = 0; i < disponiblMotorcycle.size(); i++){
                frase += disponiblMotorcycle.get(i);
                frase += "\n";
            }
            frase += "\n==============================\n";
        }

        frase += "Alocações: \n";
        if(tenanciesList.size() == 0){
            frase += "Sem alocações.\n";
            frase += "\n==============================\n";
        }else{
            for(int i = 0; i < tenanciesList.size(); i++){
                frase += tenanciesList.get(i);
                frase += "\n";
            }
            frase += "\n==============================\n";
        }
        
        frase += "Clientes: \n";
        if(tenanciesList.size() == 0){
            frase += "Sem Clientes.\n";
            frase += "\n==============================\n";
        }else{
            for(int i = 0; i < clientsList.size(); i++){
                frase += clientsList.get(i);
                frase += "\n";
            }
            frase += "\n==============================\n";
        }
        
        return frase;
    }
}
