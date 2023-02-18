package ru.job4j.testing;

public class MyClass {

    private String name;
    private String hello;

    public MyClass(String name) {
        this.name = name;
    }

    public String getHello() {
        return hello;
    }

    public static void main(String[] args) {
        System.out.println(new MyClass("name").getHello() instanceof String);
    }

    public class InnerClass {

    }
}
