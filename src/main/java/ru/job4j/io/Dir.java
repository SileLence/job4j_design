package ru.job4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Denis\\IdeaProjects\\job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(Predicate.not(File::isDirectory))
                .sorted()
                .forEach(f -> System.out.printf(
                        "%s, %d bytes%n", f.getName(), f.length()));
    }
}
