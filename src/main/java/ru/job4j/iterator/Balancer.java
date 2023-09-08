package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {

    private static int index;

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes.isEmpty()) {
            throw new IllegalArgumentException("Nodes are empty.");
        }
        while (source.hasNext()) {
            nodes.get(index).add(source.next());
            index = (nodes.size() > 1 && index < nodes.size() - 1) ? ++index : 0;
        }
    }
}
