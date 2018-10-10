package sample;

import java.util.Date;

public class Car {

    String brand, type, model, motor, color;
    Date date;
    Integer dayCount;

    public Car(){}

    public Car(String brand, String type, String model, String motor, String color,
               Date date, Integer dayCount){
        this.brand = brand;
        this.type = type;
        this.model = model;
        this.motor = motor;
        this.color = color;
        this.date = date;
        this.dayCount = dayCount;
    }
}
