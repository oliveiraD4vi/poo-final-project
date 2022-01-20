package Vehicles;

public class Motorcycle extends Vehicle {
  Motorcycle (int id, String brand, String model, String color) {
    super(id, brand, model, color);
    setType('M');
  }

  // retorna o preço base * 5% nos meses corridos e * 10% nos meses de alta  
  public float calculatePriceByDay() {
    return 0;
  }
}
