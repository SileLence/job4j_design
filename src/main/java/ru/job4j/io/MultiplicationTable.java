package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static void main(String[] args) {
        getTableFile();
    }

    private static void getTableFile() {
        try (FileOutputStream out = new FileOutputStream(
                "C:\\Users\\Denis\\Desktop\\MultiplicationTable.txt")) {
            for (int row = 2; row < 10; row++) {
                for (int column = 2; column < 10; column++) {
                    String str = String.format(
                            "%d x %d = %d\n", row, column, row * column
                    );
                    out.write(str.getBytes());

                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
