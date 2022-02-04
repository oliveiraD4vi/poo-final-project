/**
 * A classe App representa o sistema de aluguel de veiculos, no qual é possivel
 * alugar, adionar e remover veiculos, alem de temos acesso as informações do processo
 * de alocação de veiculo, informações sobre os clientes, informações sobre os veiculos.
 * Tambem é possivel modificar algumas informações disponiveis no sistema.  
 * @author Davi Oliveira | Robson Diógenes
 * @version 1.0 data: 28/01/2022
 */

package App;

import Date.Date;
import Labels.*;
import java.io.*;
import Persons.*;
import Vehicles.*;
import java.util.*;
import RentalCompany.RentalCompany;
import Tenancy.Tenancy;

class App {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner input = new Scanner(System.in);

    // variavel que guarda o input do usuário
    char menuInput;

    // inicia a RentalCompany
    RentalCompany company = updateRentalCompany(input);
    updateClientsList(company.getClientsList());
    updateVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle());
    updateTenanciesList(company);

    // menu
    do {
      System.out.print("\n---------------------------");
      System.out.println("\nVehicle Rental System - VRS\n");
      System.out.print("Manager: ");
      System.out.println(company.getManager());
      System.out.println(company.getAddress());
      System.out.println("\nWhat do you want to do?");
      System.out.println("1. Register rent (tenancy)");
      System.out.println("2. Edit disponible data");
      System.out.println("3. Edit company informations");
      System.out.println("0. Quit");
      System.out.print("\nOption: ");
      menuInput = input.nextLine().charAt(0);
        
      switch(menuInput) {
        case '1': tenancyMenu(company, input, menuInput); break;
        case '2': editMenu(company, input, menuInput);    break;
        case '3': companyMenu(company, input, menuInput); break;
        case '0':
          writeRentalCompany(company);
          writeClientsList(company.getClientsList());
          writeTenanciesList(company.getTenanciesList());
          writeVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle());
          System.out.print("\nleaving...\n");
          break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');

    input.close();
  }

  /**
   * O metodo tenacyMenu nos mostra o menu do objeto da classe Tenacy
   * @param company é um objeto da classe RentalCompany
   * @param input é um objeto da classe Scanner
   * @param menuInput é uma variavel do tipo char que representa a opção escolhida no menu
   */
  static void tenancyMenu(RentalCompany company, Scanner input, char menuInput) {
    do {
      System.out.println("\nVehicle Rental System - VRS");
      System.out.println("1. Start a new tenancy");
      System.out.println("2. Show tenancies historic");
      System.out.println("3. Show clients historic");
      System.out.println("4. Show vehicles");
      System.out.println("0. Return to main menu");
      System.out.print("\nOption: ");
      menuInput = input.nextLine().charAt(0);
        
      switch(menuInput) {
        case '1': startNewTenancy(company, input); break;
        case '2': company.showTenancies();         break;
        case '3': company.showClientsList();       break;
        case '4': 
          System.out.println("\n+ for available\n- for not available\n");
          company.showCars();
          System.out.println();
          company.showMotorcycle();
          break;
        case '0':
          writeRentalCompany(company);
          writeClientsList(company.getClientsList());
          writeTenanciesList(company.getTenanciesList());
          writeVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle());
          System.out.print("\nreturning...\n");
          break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');
  }

  /**
   * O metodo companyMenu nos mostra o menu do objeto da classse RentalCompany
   * @param company é um objeto da classe RentalCompany
   * @param input é um objeto da classe Scanner
   * @param menuInput é uma variavel do tipo char que representa a opção escolhida no menu
   */
  static void companyMenu(RentalCompany company, Scanner input, char menuInput) {
    do {
      System.out.println("\nVehicle Rental System - VRS");
      System.out.println("1. Change manager");
      System.out.println("2. Change address");
      System.out.println("0. Return to main menu");
      System.out.print("\nOption: ");
      menuInput = input.nextLine().charAt(0);
        
      switch(menuInput) {
        case '1':
          System.out.print("\nInsert manager name: ");
          String tempName = input.nextLine();
          System.out.print("Insert manager CPF: ");
          String tempCPF = input.nextLine();
          System.out.print("Insert manager email: ");
          String tempEmail = input.nextLine();
          System.out.print("Insert manager phone: ");
          String tempPhone = input.nextLine();

          Manager newManager = new Manager(tempName, tempCPF, tempEmail, tempPhone);

          company.setManager(newManager);
          System.out.print("\n----successful----\n");
          break;
        case '2': 
          System.out.print("Insert the new address: ");
          String newAddress = input.nextLine();
          company.setAddress(newAddress);
          System.out.print("\n----successful----\n");
          break;
        case '0':
          writeRentalCompany(company);
          System.out.print("\nreturning...\n");
          break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');
  }

  /**
   * Nos mostra o menu no qual é possivel modificar algumas informações do objeto da classe RentalCompany
   * @param company é um objeto da classe RentalCompany
   * @param input é um objeto da classe Scanner
   * @param menuInput é uma variavel do tipo char que representa a opção escolhida no menu
   */
  static void editMenu(RentalCompany company, Scanner input, char menuInput) {
    do {
      System.out.println("\nVehicle Rental System - VRS");
      System.out.println("1. Add new car");
      System.out.println("2. Remove a car");
      System.out.println("3. Add new motorcycle");
      System.out.println("4. Remove a motorcycle");
      System.out.println("5. Update an tenancy");
      System.out.println("0. Return to main menu");
      System.out.print("\nOption: ");
      menuInput = input.nextLine().charAt(0);
        
      switch(menuInput) {
        case '1': addVehicle(company, 'C', input);    break;
        case '2': removeVehicle(company, 'C', input); break;
        case '3': addVehicle(company, 'M', input);    break;
        case '4': removeVehicle(company, 'M', input); break;
        case '5': updateTenancy(company, input);      break;
        case '0':
          writeTenanciesList(company.getTenanciesList());
          writeVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle());
          System.out.print("\nreturning...\n");
          break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');
  }

  /**
   * O metodo addVehivle adiciona um veiculo no objeto da Classe RentalCompany
   * @param company é objeto da classe RentalCompany 
   * @param type é um char que identifca se o veiculo é um carro ('C') ou uma moto ('M')
   * @param in é um objeto da classe Scanner
   */
  static void addVehicle(RentalCompany company, char type, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    int id = 0;
    
    for (Car item : company.getDisponibleCarList())
      if (item.getId() > id) id = item.getId();
    for (Motorcycle item : company.getDisponibleMotorcycle())
      if (item.getId() > id) id = item.getId();

    id++;

    System.out.print("Brand: ");
    String tempBrand = in.nextLine();
    System.out.print("Model: ");
    String tempModel = in.nextLine();
    System.out.print("Color: ");
    String tempColor = in.nextLine();
    System.out.print("Plate (format AAA-0000): ");
    String plate = in.nextLine();

    boolean brandFound = false;
    Brand brand = Brand.chevrolet;

    for (Brand item : Brand.values())
      if (item.getName().toLowerCase().equals(tempBrand.toLowerCase())) {
        brandFound = true;
        brand = item;
      }

    boolean colorFound = false;
    Color color = Color.black;

    for (Color item : Color.values())
      if (item.getName().toLowerCase().equals(tempColor.toLowerCase())) {
        colorFound = true;
        color = item;
      }

    if (brandFound && colorFound) {
      if (type == 'C') {
        Car newCar = new Car(id, brand, tempModel, color, plate);
        company.addCar(newCar);
      } else if (type == 'M') {
        Motorcycle newMotorcycle = new Motorcycle(id, brand, tempModel, color, plate);
        company.addMotorcycle(newMotorcycle);
      }
    } else {
      System.out.println("fail: invalid color or brand");
    }
  }

  /**
   * O metodo removeVehicle remove um veiculo do objeto da classe RentalCompany
   * @param company é um objeto da classe RentalCompany 
   * @param type é um char que identifca se o veiculo é um carro ('C') ou uma moto ('M')
   * @param in é um objeto da classe Scanner
   */
  static void removeVehicle(RentalCompany company, char type, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    boolean idFound = false;

    if (type == 'C') {
      if (company.getDisponibleCarList().size() != 0) {
        company.showCars();
        System.out.print("\nInsert the ID: ");
        int removedId = Integer.parseInt(in.nextLine());
        
        Car removedCar;
        for (int i = 0; i < company.getDisponibleCarList().size(); i++) {
          if (company.getDisponibleCarList().get(i).getId() == removedId) {
            idFound = true;
            removedCar = company.getDisponibleCarList().get(i);
            company.removeCar(removedCar);
            break;
          }
        }
      } else System.out.println("fail: no cars");
    }
    
    if (type == 'M') {
      if (company.getDisponibleMotorcycle().size() != 0) {
        company.showMotorcycle();
        System.out.print("\nInsert the ID: ");
        int removedId = Integer.parseInt(in.nextLine());
        
        Motorcycle removedMotocycle;
        for (int i = 0; i < company.getDisponibleMotorcycle().size(); i++) {
          if (company.getDisponibleMotorcycle().get(i).getId() == removedId) {
            idFound = true;
            removedMotocycle = company.getDisponibleMotorcycle().get(i);
            company.removeMotorcycle(removedMotocycle);
            break;
          }
        }
      } else System.out.println("fail: no motorcycles");
    }

    if (!idFound) System.out.println("fail: id not found");
  }

  /**
   * O metodo updateTenancy muda a data ou encerra um contrato
   * @param company é um objeto da classe RentalCompany 
   * @param in é um objeto da classe Scanner
   */
  static void updateTenancy(RentalCompany company, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    if (company.getTenanciesList().size() == 0) {
      System.out.println("fail: no tenancies");
    } else {
      company.showTenancies();

      System.out.print("Insert the Id: ");
      int updateId = Integer.parseInt(in.nextLine());

      boolean idFound = false;

      for (Tenancy item : company.getTenanciesList())
        if (item.getId() == updateId) {
          idFound = true;

          System.out.print("\nWhat do you want to do?");
          System.out.print("\n1. Change devolution date");
          System.out.print("\n2. End tenancy");
          System.out.print("\n\nOption: ");
          char userIn = in.nextLine().charAt(0);

          System.out.print("\nInsert devolution date");
          System.out.print("\nDay: ");
          Byte tempDay = Byte.parseByte(in.nextLine());
          System.out.print("Month: ");
          Byte tempMonth = Byte.parseByte(in.nextLine());
          System.out.print("Year: ");
          Short tempYear = Short.parseShort(in.nextLine());

          Date finalDevolution = new Date(tempDay, tempMonth, tempYear);

          if (userIn == '1') company.changeDate(item, finalDevolution);
          else if (userIn == '2') company.endTenancie(item, finalDevolution);
          else System.out.println("fail: invalid option");

          break;
        }

      if (!idFound) System.out.print("\nfail: id not found\n");
    }
  }

  /**
   * O metodo startNewTenancy começa um novo contrato de alocação 
   * @param company é um objeto da classe RentalCompany 
   * @param in é um objeto da classe Scanner
   */
  static void startNewTenancy(RentalCompany company, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    if (company.getDisponibleCarList().size() == 0 && company.getDisponibleMotorcycle().size() == 0) {
      System.out.println("fail: no available vehicles");
    } else {
      System.out.println("\n+ for available\n- for not available");
      System.out.println("\nAvailable vehicles:");
      company.showCars();
      System.out.println();
      company.showMotorcycle();
      
      int dataInput;
      List<Car> cars = new ArrayList<Car>();
      List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
      
      System.out.println();

      do {
        System.out.print("Insert the vehicle Id (0 for cancel): ");
        dataInput = Integer.parseInt(in.nextLine());

        if (dataInput == 0) break;
        
        boolean inputFound = false;

        for (int i = 0; i < company.getDisponibleCarList().size(); i++) {
          if (company.getDisponibleCarList().get(i).getId() == dataInput) {
            inputFound = true;
            if (company.getDisponibleCarList().get(i).verifyCondition() == false) {
              cars.add(company.getDisponibleCarList().get(i));
              break;
            } else {
              System.out.println("fail: car not available");
            }
          }
        }
        
        for (int i = 0; i < company.getDisponibleMotorcycle().size(); i++) {
          if (company.getDisponibleMotorcycle().get(i).getId() == dataInput) {
            inputFound = true;
            if (company.getDisponibleMotorcycle().get(i).verifyCondition() == false) {
              motorcycles.add(company.getDisponibleMotorcycle().get(i));
              break;
            } else {
              System.out.println("fail: motorcycle not available");
            }
          }
        }

        if (!inputFound) System.out.println("fail: invalid id");
      } while (cars.size()+motorcycles.size() < 2);

      if (cars.size()+motorcycles.size() == 0) {
        System.out.print("\n----rent cancelled----\n");
      } else {
        System.out.print("\nInsert client data\n");
        System.out.print("Nome: ");
        String tempName = in.nextLine();
        System.out.print("CPF: ");
        String tempCPF = in.nextLine();
        System.out.print("Email: ");
        String tempEmail = in.nextLine();
        System.out.print("Fone: ");
        String tempPhone = in.nextLine();
        System.out.println();
        
        Client client = new Client(tempName, tempCPF, tempEmail, tempPhone);
        Date presentDate = new Date((byte)5, (byte)2, (short)2021);
        
        System.out.print("Insert devolution date");
        System.out.print("\nDay: ");
        Byte tempDay = Byte.parseByte(in.nextLine());
        System.out.print("Month: ");
        Byte tempMonth = Byte.parseByte(in.nextLine());
        System.out.print("Year: ");
        Short tempYear = Short.parseShort(in.nextLine());
        
        Date devolution = new Date(tempDay, tempMonth, tempYear);

        int id = company.getTenanciesList().size();
        
        if (company.rentVehicle(cars, motorcycles, client, presentDate, devolution, id))
          System.out.print("\n----Succesful Transaction----\n");
        else
          System.out.print("\n----Failed----\n");
      }
    }

    Collections.sort(company.getDisponibleCarList());
    Collections.sort(company.getDisponibleMotorcycle());
  }

  /**
   * O metodo updateRentalCompany ler oo dados do arquivo RentalCompany.txt
   * @param input é um objeto da classe Scanner
   * @return retorna uma referencia de um objeto da classe RentalCompany
   */
  static RentalCompany updateRentalCompany(Scanner input) {
    String tempID;
    RentalCompany company = null;

    try (
        FileReader file = new FileReader("txtFiles/RentalCompany.txt");
        BufferedReader stream = new BufferedReader(file);
    ) {
      tempID = stream.readLine();

      while(tempID != null) {
        String tempName = stream.readLine();
        String tempCPF = stream.readLine();
        String tempEmail = stream.readLine();
        String tempPhone = stream.readLine();
        
        Manager manager = new Manager(tempName, tempCPF, tempEmail, tempPhone);

        String tempAddress = stream.readLine();

        RentalCompany aux = new RentalCompany(1, manager, tempAddress);
        company = aux;

        tempID = stream.readLine();
      }

      return company;
    } catch(FileNotFoundException e) {
      System.out.print("\nfail: no file was found to read\n");

      System.out.print("\nInsert manager name: ");
      String tempName = input.nextLine();
      System.out.print("Insert manager CPF: ");
      String tempCPF = input.nextLine();
      System.out.print("Insert manager email: ");
      String tempEmail = input.nextLine();
      System.out.print("Insert manager phone: ");
      String tempPhone = input.nextLine();

      Manager newManager = new Manager(tempName, tempCPF, tempEmail, tempPhone);

      System.out.print("\nInsert the address: ");
      String newAddress = input.nextLine();
      
      RentalCompany aux = new RentalCompany(1, newManager, newAddress);

      return aux;
    } catch(IOException e) {
      System.out.print("\nfail: there was a problem reading the file\n");
      
      System.out.print("\nfail: no file was found to read\n");

      System.out.print("\nInsert manager name: ");
      String tempName = input.nextLine();
      System.out.print("Insert manager CPF: ");
      String tempCPF = input.nextLine();
      System.out.print("Insert manager email: ");
      String tempEmail = input.nextLine();
      System.out.print("Insert manager phone: ");
      String tempPhone = input.nextLine();

      Manager newManager = new Manager(tempName, tempCPF, tempEmail, tempPhone);

      System.out.print("\nInsert the address: ");
      String newAddress = input.nextLine();
      
      RentalCompany aux = new RentalCompany(1, newManager, newAddress);

      return aux;
    }
  }

  /**
   * O metodo writeRentalCompany escreve dados no arquivo RentalCompany.txt
   * @param company é um objeto da classe RentalCompany 
   */
  static void writeRentalCompany(RentalCompany company) {
    try (
      FileWriter file = new FileWriter("txtFiles/RentalCompany.txt");
      PrintWriter writer = new PrintWriter(file);
    ) {
      writer.println(1);
      writer.println(company.getManager().getName());
      writer.println(company.getManager().getCPF());
      writer.println(company.getManager().getEmail());
      writer.println(company.getManager().getPhone());
      writer.println(company.getAddress());
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }

  /**
   * O metodo updateClientsList ler os dados do arquivo Clients.txt
   * @param clients é um List de objetos da classe Client
   * @return retorna true caso a leitura ocorra, false caso não
   */
  static boolean updateClientsList(List<Client> clients) {
    String tempID;

    try (
        FileReader file = new FileReader("txtFiles/Clients.txt");
        BufferedReader stream = new BufferedReader(file);
    ) {
      tempID = stream.readLine();

      while(tempID != null) {
        String tempName = stream.readLine();
        String tempCPF = stream.readLine();
        String tempEmail = stream.readLine();
        String tempPhone = stream.readLine();

        clients.add(new Client(tempName, tempCPF, tempEmail, tempPhone));

        tempID = stream.readLine();
      }

      return true;
    } catch(FileNotFoundException e) {
      System.out.print("\nfail: no file was found to read\n");
      return false;
    } catch(IOException e) {
      System.out.print("\nfail: there was a problem reading the file\n");
      return false;
    }
  }

  /**
   * O metodo writeClientsList escreve dados no arquivo Clients.txt
   * @param clients é um List de objetos da classe Client
   */
  static void writeClientsList(List<Client> clients) {
    try (
      FileWriter file = new FileWriter("txtFiles/Clients.txt");
      PrintWriter writer = new PrintWriter(file);
    ) {
      int id = 1;

      for(Client item : clients) {
        writer.println(id);
        writer.println(item.getName());
        writer.println(item.getCPF());
        writer.println(item.getEmail());
        writer.println(item.getPhone());
        id++;
      }
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }

  /**
   * O metodo updateTenanciesList ler os dados do arquivo Tenancies.txt
   * @param company é um objeto da classe RentalCompany
   * @return retorna true caso a leitura ocorra, false caso não
   */
  static boolean updateTenanciesList(RentalCompany company) {
    String tempID;

    try (
        FileReader file = new FileReader("txtFiles/Tenancies.txt");
        BufferedReader stream = new BufferedReader(file);
    ) {
      tempID = stream.readLine();

      while(tempID != null) {
        List<Car> cars = new ArrayList<Car>();
        List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();

        String tempDay = stream.readLine();
        String tempMonth = stream.readLine();
        String tempYear = stream.readLine();

        byte day = Byte.parseByte(tempDay);
        byte month = Byte.parseByte(tempMonth);
        short year = Short.parseShort(tempYear);

        Date rentDate = new Date(day, month, year);

        tempDay = stream.readLine();
        tempMonth = stream.readLine();
        tempYear = stream.readLine();

        day = Byte.parseByte(tempDay);
        month = Byte.parseByte(tempMonth);
        year = Short.parseShort(tempYear);

        Date devolutionDate = new Date(day, month, year);

        Client client = null;
        String tempCPF = stream.readLine();

        for (Client item : company.getClientsList())
          if (item.getCPF().equals(tempCPF)) client = item;

        String tempStatus = stream.readLine();

        String string = stream.readLine();
        int number = Integer.parseInt(string);
        if (number != 0) {
          for (Car item : company.getDisponibleCarList())
            if (item.getId() == number) cars.add(item);
          for (Motorcycle item : company.getDisponibleMotorcycle())
            if (item.getId() == number) motorcycles.add(item);
        } 
        
        string = stream.readLine();
        number = Integer.parseInt(string);
        if (number != 0) {
          for (Car item : company.getDisponibleCarList())
            if (item.getId() == number) cars.add(item);
          for (Motorcycle item : company.getDisponibleMotorcycle())
            if (item.getId() == number) motorcycles.add(item);
        }

        int tempIntID = Integer.parseInt(tempID);

        company.getTenanciesList().add(new Tenancy(tempIntID, rentDate, devolutionDate, client, cars, motorcycles));
        Collections.sort(company.getTenanciesList());

        if (tempStatus.equals("false"))
          for (Tenancy item : company.getTenanciesList())
            if (item.getId() == tempIntID) item.setStatus(false);

        tempID = stream.readLine();
      }

      return true;
    } catch(FileNotFoundException e) {
      System.out.print("\nfail: no file was found to read\n");
      return false;
    } catch(IOException e) {
      System.out.print("\nfail: there was a problem reading the file\n");
      return false;
    }
  }

  /**
   * O metodo writeTenanciesList escreve dados no arquivo Tenancies.txt
   * @param tenancies é um List com objetos da classe Tenancy
   */
  static void writeTenanciesList(List<Tenancy> tenancies) {
    try (
      FileWriter file = new FileWriter("txtFiles/Tenancies.txt");
      PrintWriter writer = new PrintWriter(file);
    ) {
      for(Tenancy item : tenancies) {
        writer.println(item.getId());
        writer.println(item.getRentDate().getDay());
        writer.println(item.getRentDate().getMonth());
        writer.println(item.getRentDate().getYear());
        writer.println(item.getDevolutionDate().getDay());
        writer.println(item.getDevolutionDate().getMonth());
        writer.println(item.getDevolutionDate().getYear());
        writer.println(item.getClient().getCPF());
        writer.println(item.verifyStatus());

        if (item.getCars().size()+item.getMotorcycles().size() == 1) {
          for (Car car : item.getCars())
            writer.println(car.getId());
          for (Motorcycle moto : item.getMotorcycles())
            writer.println(moto.getId());

          writer.println(0);
        } else {
          for (Car car : item.getCars())
            writer.println(car.getId());
          for (Motorcycle moto : item.getMotorcycles())
            writer.println(moto.getId());
        }
      }
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }

  /**
   * O metodo updateVehiclesList ler os dados do arquivo Vehicle.txt
   * @param cars é um List com objetos da classe Car
   * @param motorcycles é um List com objetos da classe Motorcycle
   * @return retorna true caso a leitura ocorra, false caso não
   */
  static boolean updateVehiclesList(List<Car> cars, List<Motorcycle> motorcycles) {
    String tempID;

    try (
        FileReader file = new FileReader("txtFiles/Vehicles.txt");
        BufferedReader stream = new BufferedReader(file);
    ) {
      tempID = stream.readLine();

      while(tempID != null) {
        String type = stream.readLine();
        String tempRented = stream.readLine();
        String tempBrand = stream.readLine();
        String tempModel = stream.readLine();
        String tempColor = stream.readLine();
        String tempPlate = stream.readLine();

        int tempIntID = Integer.parseInt(tempID);

        boolean brandFound = false;
        Brand brand = Brand.chevrolet;

        for (Brand item : Brand.values())
          if (item.getName().toLowerCase().equals(tempBrand.toLowerCase())) {
            brandFound = true;
            brand = item;
          }

        boolean colorFound = false;
        Color color = Color.black;

        for (Color item : Color.values())
          if (item.getName().toLowerCase().equals(tempColor.toLowerCase())) {
            colorFound = true;
            color = item;
          }

        if (brandFound && colorFound) {
          if (type.equals("C")) {
            cars.add(new Car(tempIntID, brand, tempModel, color, tempPlate));
            Collections.sort(cars);
            
            if (tempRented.equals("true")) 
              for (Car item : cars)
                if (item.getId() == tempIntID)
                  item.setRented(true);
          }
          else if (type.equals("M")) {
            motorcycles.add(new Motorcycle(tempIntID, brand, tempModel, color, tempPlate));
            Collections.sort(motorcycles);
          
            if (tempRented.equals("true")) 
              for (Motorcycle item : motorcycles)
                if (item.getId() == tempIntID)
                  item.setRented(true);
          }
        } else {
          System.out.println("fail: invalid color or brand");
        }

        tempID = stream.readLine();
      }

      return true;
    } catch(FileNotFoundException e) {
      System.out.print("\nfail: no file was found to read\n");
      return false;
    } catch(IOException e) {
      System.out.print("\nfail: there was a problem reading the file\n");
      return false;
    }
  }

  /**
   * O metodo writeVehiclesList escreve dados no arquivo Vehicle.txt
   * @param cars é um List com objetos da classe Car
   * @param motorcycles é um List com objetos da classe Motorcycle
   */
  static void writeVehiclesList(List<Car> cars, List<Motorcycle> motorcycles) {
    try (
      FileWriter file = new FileWriter("txtFiles/Vehicles.txt");
      PrintWriter writer = new PrintWriter(file);
    ) {
      for(Car item : cars) {
        writer.println(item.getId());
        writer.println(item.getType());
        writer.println(item.verifyCondition());
        writer.println(item.getBrand());
        writer.println(item.getModel());
        writer.println(item.getColor());
        writer.println(item.getPlate());
      }

      for(Motorcycle item : motorcycles) {
        writer.println(item.getId());
        writer.println(item.getType());
        writer.println(item.verifyCondition());
        writer.println(item.getBrand());
        writer.println(item.getModel());
        writer.println(item.getColor());
        writer.println(item.getPlate());
      }
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }
}
