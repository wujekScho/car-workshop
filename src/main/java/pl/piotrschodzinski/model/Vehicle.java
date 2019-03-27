package pl.piotrschodzinski.model;

import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int manufactureYear;
    private String registrationNumber;
    private LocalDate serviceDate;
    private int customerId;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int manfuctureYear, String registrationNumber, LocalDate serviceDate, int customerId) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manfuctureYear;
        this.registrationNumber = registrationNumber;
        this.serviceDate = serviceDate;
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }
}
