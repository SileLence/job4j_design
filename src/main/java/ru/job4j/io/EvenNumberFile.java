package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder chars = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                if (read != 10 && read != 13) {
                    chars.append((char) read);
                } else if (!chars.isEmpty()) {
                    int number = Integer.parseInt(chars.toString());
                    System.out.printf(
                            "Number %d is even: %b\n", number, number % 2 == 0
                    );
                    chars.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

