package pl.piotrschodzinski.model;

import java.time.LocalDate;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public Customer() {
    }

    public Customer(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        if (birthDate != null) {
            this.birthDate = birthDate;
        }
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
