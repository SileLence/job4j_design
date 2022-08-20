package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private final Predicate<Path> condition;
    private final List<Path> paths;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        this.paths = new ArrayList<>();
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            paths.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
