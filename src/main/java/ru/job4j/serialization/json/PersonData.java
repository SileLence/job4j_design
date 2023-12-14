package ru.job4j.serialization.json;

import java.math.BigDecimal;

public class PersonData {
    
    private BigDecimal salary;
    private Person person;
    
    public PersonData(BigDecimal salary, Person person) {
        this.salary = salary;
        this.person = person;
    }
    
    @Override
    public String toString() {
        return "PersonData{"
            + "salary=" + salary
            + ", person=" + person
            + '}';
    }
}
