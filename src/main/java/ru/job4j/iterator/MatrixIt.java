package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = row; i < data.length; i++) {
            for (int j = column; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    row = i;
                    column = j;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else if (column + 1 < data[row].length) {
            return data[row][column++];
        } else if (row + 1 < data.length) {
            column = 0;
            return data[row++][column];
        }
        return data[row][column];
    }
}
