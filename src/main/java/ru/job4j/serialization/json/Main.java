package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        final PersonData personDataObject = new PersonData(
            BigDecimal.valueOf(2500.00),
            new Person(true, 27,
                new Contact("7-999-123-45-67"),
                new String[]{"Musician", "Programmer"})
        );
        
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(personDataObject));
        
        String personDataJson = "{\""
            + "salary\":3120.0,\""
            + "person\":{\""
                + "sex\":false,\""
                + "age\":29,\""
                + "contact\":{\""
                    + "phone\":\"7-747-098-76-54\"},\""
                + "statuses\":[\"Skater\",\"Business Analyst\"]}"
            + "}";
        
        PersonData personData = gson.fromJson(personDataJson, PersonData.class);
        System.out.println(personData);
    }
}
