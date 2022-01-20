package Vehicles;

abstract public class Vehicle {
  protected int id;
  protected char type; //C or M
  protected String brand;
  protected String model;
  protected String color;
  protected boolean rented = false;
  protected final float basePrice = (float)14.5;

  Vehicle (int id, String brand, String model, String color) {
    setId(id);
    setBrand(brand);
    setModel(model);
    setColor(color);
  }
  
  abstract public float calculatePriceByDay();

  public boolean verifyCondition() {
    return this.rented;
  }

  public String toString() {
    return "";
  }

  // setters
  public void setId(int id) {
    this.id = id;
  }

  public void setType(char type) {
    this.type = type;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setRented(boolean rented) {
    this.rented = rented;
  }

  // getters
  public int getId() {
    return this.id;
  }

  public char getType() {
    return this.type;
  }

  public String getBrand() {
    return this.brand;
  }

  public String getModel() {
    return this.model;
  }

  public String getColor() {
    return this.color;
  }
}
