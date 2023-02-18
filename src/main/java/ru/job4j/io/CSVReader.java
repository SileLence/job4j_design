package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    private static String delimiter;
    private static List<String> filters;

    public static void handle(ArgsName args) throws IOException {
        validateParams(args);
        Path sourceFile = new File(args.get("path")).toPath();
        delimiter = args.get("delimiter");
        filters = Arrays.asList(args.get("filter").split(","));
        List<String> allRows = new ArrayList<>();
        try (Scanner scan = new Scanner(sourceFile)) {
            while (scan.hasNext()) {
                allRows.add(scan.nextLine());
            }
        }
        List<Integer> filteredIndexes = calculateFilteredIndexes(allRows);
        List<String> filteredRows = filterOutput(allRows, filteredIndexes);
        if ("stdout".equals(args.get("out"))) {
            for (String row : filteredRows) {
                System.out.println(row);
            }
        } else {
            try (PrintWriter writer = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(args.get("out"))))) {
                for (String row : filteredRows) {
                    writer.write(row);
                }
            }
        }
    }

    private static List<Integer> calculateFilteredIndexes(List<String> rows) {
        List<Integer> filteredIndexes = new ArrayList<>(filters.size());
        String[] columns = rows.get(0).split(delimiter);
        for (String filter : filters) {
            for (int i = 0; i < columns.length; i++) {
                if (filter.equals(columns[i])) {
                    filteredIndexes.add(i);
                }
            }
        }
        return filteredIndexes;
    }

    private static List<String> filterOutput(List<String> rows,
                                             List<Integer> filteredIndexes) {
        List<String> filteredOutput = new ArrayList<>();
        for (String row : rows) {
            List<String> result = new ArrayList<>(filteredIndexes.size());
            for (Integer filteredIndex : filteredIndexes) {
                String[] columns = row.split(delimiter);
                for (int columnIndex = 0; columnIndex < columns.length; columnIndex++) {
                    if (filteredIndex == columnIndex) {
                        result.add(columns[columnIndex]);
                    }
                }
            }
            filteredOutput.add(String.join(delimiter, result) + System.lineSeparator());
        }
        return filteredOutput;
    }

    private static void validateParams(ArgsName params) {
        if (!new File(params.get("path")).isFile()) {
            throw new IllegalArgumentException("Incorrect input file.");
        }
        String del = params.get("delimiter");
        if (!",".equals(del) && !";".equals(del) && !"\t".equals(del)) {
            throw new IllegalArgumentException("Unsupported delimiter.");
        }
        String out = params.get("out");
        if (!"stdout".equals(out) && !new File(out).isFile()) {
            throw new IllegalArgumentException("Unsupported output.");
        }
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}
