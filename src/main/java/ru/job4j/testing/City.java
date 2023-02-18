package ru.job4j.testing;

public class City {

    class Hospital {
        private String name;

        public Hospital(String name) {
            this.name = name;
        }

        public void sayName() {
            System.out.println(name);
        }
    }
}
