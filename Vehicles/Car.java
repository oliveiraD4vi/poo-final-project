package Vehicles;

public class Car extends Vehicle {
  Car (int id, String brand, String model, String color) {
    super(id, brand, model, color);
    setType('C');
  }

  // retorna o preço base * 10% nos meses corridos e * 15% nos meses de alta  
  public float calculatePriceByDay() {
    return 0;
  }
}
