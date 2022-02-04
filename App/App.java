package App;

import Date.Date;
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
    Manager manager = new Manager("Kevin", "04896138376", "kevinarruda@email.com", "888928292");
    RentalCompany company = new RentalCompany(1, manager, "Rua Pedro Alves Feitosa, 232 - Centro, Fortaleza - CE - Brasil");

    if (!updateVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle()))
      System.out.print("\n----reading file failed----\n");

    // menu
    do {
      System.out.println("\nVehicle Rental System - VRS");
      System.out.println("\nMain actions");
      System.out.println("1. Start a new tenancy");
      System.out.println("2. Show tenancies historic");
      System.out.println("3. Show clients historic");
      System.out.println("4. Show vehicles");
      System.out.println("0. Quit");
      System.out.println("\nEdition actions");
      System.out.println("5. Add new car");
      System.out.println("6. Remove a car");
      System.out.println("7. Add new motorcycle");
      System.out.println("8. Remove a motorcycle");
      System.out.println("9. Update an tenancy");
      System.out.print("\nOption: ");
      menuInput = input.next().charAt(0);
        
      switch(menuInput) {
        case '1': startNewTenancy(company, input); break;
        case '2': company.showTenancies(); break;
        case '3': company.showClientsList(); break;
        case '4': 
          System.out.println("\n+ for available\n- for not available\n");
          company.showCars();
          System.out.println();
          company.showMotorcycle();
          break;
        case '5': addVehicle(company, 'C', input); break;
        case '6': removeVehicle(company, 'C', input); break;
        case '7': addVehicle(company, 'M', input); break;
        case '8': removeVehicle(company, 'M', input); break;
        case '9': updateTenancy(company, input); break;
        case '0': 
          writeVehiclesList(company.getDisponibleCarList(), company.getDisponibleMotorcycle());
          System.out.print("\nleaving...\n");
          break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');

    input.close();
  }

  static void addVehicle(RentalCompany company, char type, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    int id = 0;
    
    for (Car item : company.getDisponibleCarList())
      if (item.getId() > id) id = item.getId();
    for (Motorcycle item : company.getDisponibleMotorcycle())
      if (item.getId() > id) id = item.getId();

    id++;

    System.out.print("Brand: ");
    String brand = in.next();
    System.out.print("Model: ");
    String model = in.next();
    System.out.print("Color: ");
    String color = in.next();
    System.out.print("Plate (format AAA-0000): ");
    String plate = in.next();

    if (type == 'C') {
      Car newCar = new Car(id, brand, model, color, plate);
      company.addCar(newCar);
    } else if (type == 'M') {
      Motorcycle newMotorcycle = new Motorcycle(id, brand, model, color, plate);
      company.addMotorcycle(newMotorcycle);
    }
  }

  static void removeVehicle(RentalCompany company, char type, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    boolean idFound = false;

    if (type == 'C') {
      if (company.getDisponibleCarList().size() != 0) {
        company.showCars();
        System.out.print("\nInsert the ID: ");
        int removedId = in.nextInt();
        
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
        int removedId = in.nextInt();
        
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

  static void updateTenancy(RentalCompany company, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    if (company.getTenanciesList().size() == 0) {
      System.out.println("fail: no tenancies");
    } else {
      company.showTenancies();

      System.out.print("Insert the Id: ");
      int updateId = in.nextInt();
      System.out.print("\nInsert devolution date");
      System.out.print("\nDay: ");
      Byte tempDay = in.nextByte();
      System.out.print("Month: ");
      Byte tempMonth = in.nextByte();
      System.out.print("Year: ");
      Short tempYear = in.nextShort();

      Date finalDevolution = new Date(tempDay, tempMonth, tempYear);

      boolean idFound = false;

      for (Tenancy item : company.getTenanciesList())
        if (item.getId() == updateId) {
          idFound = true;
          company.endTenancie(item, finalDevolution);
        }

      if (!idFound) System.out.print("\nfail: id not found\n");
    }
  }

  static void startNewTenancy(RentalCompany company, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");

    if (company.getDisponibleCarList().size() == 0 && company.getDisponibleMotorcycle().size() == 0) {
      System.out.println("fail: no available vehicles");
    } else {
      System.out.println("Veículos disponíveis:");
      System.out.println("\n+ for available\n- for not available\n");
      company.showCars();
      System.out.println();
      company.showMotorcycle();
      
      int dataInput;
      List<Car> cars = new ArrayList<Car>();
      List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
      
      System.out.println();

      do {
        System.out.print("Insira o ID do veículo que quer alugar (0 para cancelar): ");
        dataInput = in.nextInt();

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
        String tempName = in.next();
        System.out.print("CPF: ");
        String tempCPF = in.next();
        System.out.print("Email: ");
        String tempEmail = in.next();
        System.out.print("Fone: ");
        String tempPhone = in.next();
        System.out.println();
        
        Client client = new Client(tempName, tempCPF, tempEmail, tempPhone);
        Date presentDate = new Date((byte)5, (byte)2, (short)2021);
        
        System.out.print("Insert devolution date");
        System.out.print("\nDay: ");
        Byte tempDay = in.nextByte();
        System.out.print("Month: ");
        Byte tempMonth = in.nextByte();
        System.out.print("Year: ");
        Short tempYear = in.nextShort();
        
        Date devolution = new Date(tempDay, tempMonth, tempYear);
        
        if (company.rentVehicle(cars, motorcycles, client, presentDate, devolution))
          System.out.print("\n----Succesful Transaction----\n");
        else
          System.out.print("\n----Failed----\n");
      }
    }

    Collections.sort(company.getDisponibleCarList());
    Collections.sort(company.getDisponibleMotorcycle());
  }

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

        if (type.equals("C")) {
          cars.add(new Car(tempIntID, tempBrand, tempModel, tempColor, tempPlate));
          Collections.sort(cars);
          
          if (tempRented.equals("true")) 
            for (Car item : cars)
              if (item.getId() == tempIntID)
                item.setRented(true);
        }
        else if (type.equals("M")) {
          motorcycles.add(new Motorcycle(tempIntID, tempBrand, tempModel, tempColor, tempPlate));
          Collections.sort(motorcycles);
        
          if (tempRented.equals("true")) 
            for (Motorcycle item : motorcycles)
              if (item.getId() == tempIntID)
                item.setRented(true);
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
