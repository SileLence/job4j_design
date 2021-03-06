package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read
                     = new BufferedReader(new FileReader(this.path))) {
            List<String> strings = read.lines().toList();
            for (String str : strings) {
                str = str.replaceAll(" ", "");
                if (!str.startsWith("#") && !str.isEmpty()) {
                    List<String> s = Arrays.stream(
                            str.split("=", 2)).toList();
                    if (s.size() < 2 || s.contains("")) {
                        throw new IllegalArgumentException(
                                "The file contains errors"
                        );
                    }
                    values.put(s.get(0), s.get(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read
                     = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(
                new Config("src/main/resources/test_clean.properties")
        );
    }
}
