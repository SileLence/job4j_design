package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<Path, FileProperty> files = new HashMap<>();
    private final Set<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(Files.size(file), file.toFile().getName());
        if (files.containsValue(property)) {
            duplicates.add(property);
        }
        files.put(file, property);
        return super.visitFile(file, attrs);
    }

    public Map<Path, FileProperty> getFiles() {
        return files;
    }

    public Set<FileProperty> getDuplicates() {
        return duplicates;
    }
}
