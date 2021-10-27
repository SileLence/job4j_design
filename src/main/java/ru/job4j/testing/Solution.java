package ru.job4j.testing;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.nextLine());
        int width = Integer.parseInt(sc.nextLine());
        int segmentsToCut = Integer.parseInt(sc.nextLine());

        if (segmentsToCut == length
                || segmentsToCut == width) {
            System.out.println("YES");
        } else if (segmentsToCut <= length * width
                && (segmentsToCut % length == 0
                || segmentsToCut % width == 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
