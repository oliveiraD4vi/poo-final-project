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
    int idCounter = 0;

    // variavel que guarda o input do usuário
    char menuInput;

    // inicia a RentalCompany
    Manager manager = new Manager("Kevin", "04896138376", "kevinarruda@email.com", "888928292");
    RentalCompany company = new RentalCompany(1, manager, "Rua Pedro Alves Feitosa, 232 - Centro, Fortaleza - CE - Brasil");

    boolean updateCar = updateCarsList(company.getDisponibleCarList());
    boolean updateMoto = updateMotorcyclesList(company.getDisponibleMotorcycle());

    if (!updateCar && !updateMoto)
      System.out.print("\n----reading file failed----\n");
    else idCounter =
        company.getDisponibleCarList().size() + company.getDisponibleMotorcycle().size();

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
          company.showMotorcycle();
          break;
        case '5': addVehicle(company, 'C', idCounter, input); break;
        case '6': removeVehicle(company, 'C', idCounter, input); break;
        case '7': addVehicle(company, 'M', idCounter, input); break;
        case '8': removeVehicle(company, 'M', idCounter, input); break;
        case '9': updateTenancy(company, input); break;
        case '0': System.out.print("\nleaving...\n"); break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '0');

    input.close();
  }

  static void addVehicle(RentalCompany company, char type, int id, Scanner in) {
    System.out.println("\nVehicle Rental System - VRS");
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

  static void removeVehicle(RentalCompany company, char type, int id, Scanner in) {
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
  }

  static boolean updateCarsList(List<Car> disponibleVehicles) {
    int tempIntID;
    String tempID;
    String tempBrand;
    String tempModel;
    String tempColor;
    String tempPlate;

    try (
        FileReader carFile = new FileReader("txtFiles/Cars.txt");
        BufferedReader carStream = new BufferedReader(carFile);
    ) {
      tempID = carStream.readLine();

      while(tempID != null) {
        tempBrand = carStream.readLine();
        tempModel = carStream.readLine();
        tempColor = carStream.readLine();
        tempPlate = carStream.readLine();

        tempIntID = Integer.parseInt(tempID);

        disponibleVehicles.add(new Car(tempIntID, tempBrand, tempModel, tempColor, tempPlate));
        tempID = carStream.readLine();
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

  static boolean updateMotorcyclesList(List<Motorcycle> disponibleVehicles) {
    int tempIntID;
    String tempID;
    String tempBrand;
    String tempModel;
    String tempColor;
    String tempPlate;

    try (
        FileReader motoFile = new FileReader("txtFiles/Motorcycles.txt");
        BufferedReader motoStream = new BufferedReader(motoFile);
    ) {
      tempID = motoStream.readLine();

      while(tempID != null) {
        tempBrand = motoStream.readLine();
        tempModel = motoStream.readLine();
        tempColor = motoStream.readLine();
        tempPlate = motoStream.readLine();

        tempIntID = Integer.parseInt(tempID);

        disponibleVehicles.add(new Motorcycle(tempIntID, tempBrand, tempModel, tempColor, tempPlate));
        tempID = motoStream.readLine();
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

  // testing
  static void writeCarsList(List<Car> disponibleCar) {
    try (
      FileWriter carFile = new FileWriter("txtFiles/Cars.txt");
      PrintWriter carWriter = new PrintWriter(carFile);
    ) {
      for(Car item : disponibleCar) {
        carWriter.println(item.getId());
        carWriter.println(item.getBrand());
        carWriter.println(item.getModel());
        carWriter.println(item.getColor());
        carWriter.println(item.getPlate());
      }
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }

  // testing
  static void writeMotorcyclesList(List<Motorcycle> disponibleMotorcycle) {
    try (
      FileWriter motoFile = new FileWriter("txtFiles/Motorcycles.txt");
      PrintWriter motoWriter = new PrintWriter(motoFile);
    ) {
      for(Motorcycle item : disponibleMotorcycle) {
        motoWriter.println(item.getId());
        motoWriter.println(item.getBrand());
        motoWriter.println(item.getModel());
        motoWriter.println(item.getColor());
        motoWriter.println(item.getPlate());
      }
    } catch(IOException e) {
      System.out.println("fail: there was a problem writing the file");
    }
  }
}
