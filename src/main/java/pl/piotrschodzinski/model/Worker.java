package pl.piotrschodzinski.model;

public class Worker {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String note;
    private double ratePerHour;

    public Worker() {
    }

    public Worker(String name, String surname, String adress, String phoneNumber, String note, double ratePerHour) {
        this.name = name;
        this.surname = surname;
        this.address = adress;
        this.phoneNumber = phoneNumber;
        if (note != null) {
            this.note = note;
        }
        this.ratePerHour = ratePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
}
