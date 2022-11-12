package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\Users\\Denis\\.m2"), visitor);
        for (FileProperty property : visitor.getDuplicates()) {
            System.out.println(property);
            for (Map.Entry<Path, FileProperty> file : visitor.getFiles().entrySet()) {
                if (property.equals(file.getValue())) {
                    System.out.println(file.getKey());
                }
            }
        }
    }
}
