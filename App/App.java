package App;

import Date.Date;
import Persons.*;
import Vehicles.*;
import java.util.*;
import RentalCompany.RentalCompany;
// import java.io.*;

class App {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner input = new Scanner(System.in);

    // variavel que guarda o input do usuário
    char menuInput;

    // inicia a RentalCompany
    Manager manager = new Manager("Kevin", "04896138376", "kevinarruda@email.com", "888928292");
    RentalCompany company = new RentalCompany(1, manager, "Rua Pedro Alves Feitosa, 232 - Centro, Fortaleza - CE - Brasil");

    // verifica situação dos veículos
    // se já existir um arquivo com carros e etc, insere, se não, pede ao usuário que insira pelo ou menos um carro e uma moto na lista

    // menu
    do {
      System.out.println("\nVehicle Rental System - VRS");
      System.out.println("1. Start a new tenancy");
      System.out.println("2. Show tenancies historic");
      System.out.println("3. Show clients historic");
      System.out.println("4. Update a current tenancy");
      System.out.println("5. Quit");
      System.out.print("Entre your choice: ");
      menuInput = input.next().charAt(0);
        
      switch(menuInput) {
        case '1': startNewTenancy(company); break;
        case '2': company.showTenancies(); break;
        case '3': company.showClientsList(); break;
        case '4': System.out.println("You choosed 4"); break;
        case '5': System.out.println("You choosed 5"); break;
        default : System.out.print("\n----invalid choice----\n");
      }
    } while (menuInput != '5');

    input.close();
  }

  private static void startNewTenancy(RentalCompany company) {
    System.out.println("\nVehicle Rental System - VRS");

    if (company.getDisponibleCarList().size() == 0 && company.getDisponibleMotorcycle().size() == 0) {
      System.out.println("fail: não há veículos disponíveis");
    } else {
      System.out.println("Veículos disponíveis:");
      company.showCars();
      company.showMotorcycle();
      
      int dataInput;
      List<Car> cars = new ArrayList<Car>();
      List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
      
      Scanner input = new Scanner(System.in);
      
      do {
        System.out.println("Insira o ID do veículo que quer alugar:");
        System.out.println("// digite -1 para parar");
        dataInput = input.nextInt();
        
        for (int i = 0; i < company.getDisponibleCarList().size(); i++) {
          if (company.getDisponibleCarList().get(i).getId() == dataInput) {
            cars.add(company.getDisponibleCarList().get(i));
            break;
          }
        }
        
        for (int i = 0; i < company.getDisponibleMotorcycle().size(); i++) {
          if (company.getDisponibleMotorcycle().get(i).getId() == dataInput) {
            motorcycles.add(company.getDisponibleMotorcycle().get(i));
            break;
          }
        }
      } while (dataInput != -1);
      
      System.out.println("Insira as informações do cliente");
      System.out.print("\nNome: ");
      String tempName = input.next();
      System.out.print("\nCPF: ");
      String tempCPF = input.next();
      System.out.print("\nEmail: ");
      String tempEmail = input.next();
      System.out.print("\nFone: ");
      String tempPhone = input.next();
      System.out.println();
      
      Client client = new Client(tempName, tempCPF, tempEmail, tempPhone);
      Date presentDate = new Date((byte)5, (byte)2, (short)2021);
      
      System.out.println("Insira a data de devolução");
      System.out.print("Dia: ");
      Byte tempDay = input.nextByte();
      System.out.print("Mês: ");
      Byte tempMonth = input.nextByte();
      System.out.print("Ano: ");
      Short tempYear = input.nextShort();
      
      Date devolution = new Date(tempDay, tempMonth, tempYear);
      
      boolean test = company.rentVehicle(cars, motorcycles, client, presentDate, devolution);
      
      if (test) System.out.print("\n----Succesful Transaction----\n");
      else System.out.print("\n----Failed----\n");
      
      input.close();
    }
  }
}
