package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Analyze {

    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            List<String> lines = read.lines().toList();
            int startWrite = 0;
            for (String line : lines) {
                boolean isAvailable
                        = line.startsWith("200") || line.startsWith("300");
                if (!isAvailable && startWrite == 0) {
                    writer.write(String.format("%s;", line.split(" ")[1]));
                    startWrite = 1;
                } else if (isAvailable && startWrite != 0) {
                    writer.write(String.format("%s;%n", line.split(" ")[1]));
                    startWrite = 0;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("server.log", "unavailable.csv");
    }
}
