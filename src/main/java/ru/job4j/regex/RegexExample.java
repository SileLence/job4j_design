package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        example1();
        example2();
        example3();
        example4();
        example5();
        example6();
        example7();
        example8();
        example9();
    }

    public static void example1() {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");
        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);
        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2 + System.lineSeparator());
    }

    public static void example2() {
        Pattern pattern1 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        String text3 = "Job4j";
        Matcher matcher3 = pattern1.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);
        String text4 = "joB4J";
        Matcher matcher4 = pattern1.matcher(text4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4 + System.lineSeparator());
    }

    public static void example3() {
        Pattern pattern1 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        String text3 = "Job4j";
        Matcher matcher3 = pattern1.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);
        String text4 = "joB4J";
        Matcher matcher4 = pattern1.matcher(text4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4 + System.lineSeparator());
    }

    public static void example4() {
        Pattern pattern2 = Pattern.compile("Job4j");
        String text = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher = pattern2.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group()
                    + " iStart: " + matcher.start()
                    + " iEnd: " + matcher.end());
        }
        System.out.println(System.lineSeparator());
    }

    public static void example5() {
        Pattern pattern3 = Pattern.compile("123");
        String text5 = "1231 и 1232 и 1233";
        Matcher matcher5 = pattern3.matcher(text5);
        String rsl = matcher5.replaceAll("Job4j");
        System.out.println(rsl + System.lineSeparator());
    }

    public static void example6() {
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));
    }

    public static void example7() {
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    public static void example8() {
        Pattern pattern = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }

    public static void example9() {
        Pattern pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }
}
