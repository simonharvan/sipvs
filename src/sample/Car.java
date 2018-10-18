package sample;

import java.time.LocalDate;
import java.util.Date;

public class Car {

    String brand, type, model, motor, color;
    LocalDate date;
    String dayCount;
    int pricePerDay;

    public Car(){}

    public Car(String brand, String type, String model, String motor, String color,
               LocalDate date, String dayCount, int pricePerDay){
        this.brand = brand;
        this.type = type;
        this.model = model;
        this.motor = motor;
        this.color = color;
        this.date = date;
        this.dayCount = dayCount;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        this.dayCount = dayCount;
    }

    public Car(String brand, String type, String model, String motor, String color,
               LocalDate date, String dayCount){
        this.brand = brand;
        this.type = type;
        this.model = model;
        this.motor = motor;
        this.color = color;
        this.date = date;
        this.dayCount = dayCount;
    }
}
