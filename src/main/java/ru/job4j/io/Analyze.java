package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Analyze {

    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean startWrite = false;
            for (String line : read.lines().toList()) {
                boolean isAvailable = line.startsWith("200") || line.startsWith("300");
                if (!isAvailable && !startWrite) {
                    writer.write(String.format("%s;", line.split(" ")[1]));
                    startWrite = true;
                } else if (isAvailable && startWrite) {
                    writer.write(String.format("%s;%n", line.split(" ")[1]));
                    startWrite = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("server.log", "unavailable.csv");
    }
}
