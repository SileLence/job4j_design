package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Not provided folder path or file extension");
        }
        if (!args[0].matches("^([a-z]|[A-Z]):\\\\.*")) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!args[1].matches("\\.([a-z]|[A-Z]|[0-9])+")) {
            throw new IllegalArgumentException("Incorrect file extension");
        }
    }
}
