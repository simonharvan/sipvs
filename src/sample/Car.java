package sample;

import java.time.LocalDate;
import java.util.Date;

public class Car {

    String brand, type, model, motor, color;
    LocalDate date;
    String dayCount;

    public Car(){}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand.replace("<(.*)>", "");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.replace("<(.*)>", "");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.replace("<(.*)>", "");
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor.replace("<(.*)>", "");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.replace("<(.*)>", "");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayCount() {
        return dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount.replace("<(.*)>", "");
    }

    public Car(String brand, String type, String model, String motor, String color,
               LocalDate date, String dayCount){
        this.brand = brand.replace("<(.*)>", "");
        this.type = type.replace("<(.*)>", "");
        this.model = model.replace("<(.*)>", "");
        this.motor = motor.replace("<(.*)>", "");
        this.color = color.replace("<(.*)>", "");
        this.date = date;
        this.dayCount = dayCount.replace("<(.*)>", "");
    }
}
