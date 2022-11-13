package ru.job4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(Predicate.not(File::isDirectory))
                .sorted()
                .forEach(f -> System.out.printf(
                        "%s, %d bytes%n", f.getName(), f.length()));
    }
}
