package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.putNextEntry(new ZipEntry(source.toString()));
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName params = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validateParams(params);
        Predicate<Path> excludedFiles = p -> !p.toString().endsWith(params.get("e"));
        zip.packFiles(
                Search.search(Paths.get(params.get("d")), excludedFiles),
                Paths.get(params.get("o"))
        );
    }

    private void validateParams(ArgsName params) {
        if (!new File(params.get("d")).isDirectory()) {
            throw new IllegalArgumentException("Directory is not exists");
        }
        if (!(params.get("e").startsWith(".") && params.get("e").length() > 1)) {
            throw new IllegalArgumentException("Incorrect value of '-e' key");
        }
        if (params.get("o").contains("\\")) {
            String outputPath = params.get("o");
            outputPath = outputPath.substring(0, outputPath.lastIndexOf("\\"));
            if (!new File(outputPath).isDirectory()) {
                throw new IllegalArgumentException("Incorrect output file path");
            }
        }
        if (!(params.get("o").endsWith(".zip") && params.get("o").length() > 4)) {
            throw new IllegalArgumentException("Incorrect output file format");
        }
    }
}
