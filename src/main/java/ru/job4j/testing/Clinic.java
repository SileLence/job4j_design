package ru.job4j.testing;

public class Clinic extends City.Hospital {

    private String clinicName;

    public Clinic(City city, String clinicName) {
        city.super(clinicName);
    }
}
