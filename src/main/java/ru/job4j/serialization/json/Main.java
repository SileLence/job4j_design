package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        
        /* 5. Преобразование JSON в POJO. JsonObject [#315064] */
        System.out.println("--------");
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        
        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30,
            new Contact("11-111"),
            new String[]{"Worker", "Married"}
        );
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        
        /* Выведем результат в консоль */
        System.out.println(jsonObject);
        
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person));
        
        /* Код для выполнения задания */
        System.out.println("--------");
        
        ElectronicComponent piezo = new ElectronicComponent("Piezo");
        ElectronicComponent mic = new ElectronicComponent("Microphone");
        Guitar electroAcousticGuitar = new Guitar("Furch", "Cedar", List.of(piezo, mic));
        System.out.println(new JSONObject(electroAcousticGuitar));
        
        Guitar acousticGuitar = new Guitar("Cort", "Spruce");
        JSONObject acousticGuitarJson = new JSONObject();
        acousticGuitarJson.put("modelName", acousticGuitar.getModelName());
        acousticGuitarJson.put("wood", acousticGuitar.getWood());
        acousticGuitarJson.put("isElectric", acousticGuitar.isElectric());
        acousticGuitarJson.put("electronicComponents", acousticGuitar.getElectronicComponents());
        System.out.println(acousticGuitarJson);
    }
}
