package ru.job4j.testing;

public class Solution {

    public static void main(String[] args) {
        City.Hospital myClass = new City().new Hospital("name");
        myClass.sayName();

        Clinic clinic = new Clinic(new City(), "name");
        clinic.sayName();
    }
}
