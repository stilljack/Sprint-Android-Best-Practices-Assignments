package com.another.javreviewguided;

public class Employee {
    public Employee(int age, String whatever) {
        Age = age;
        this.whatever = whatever;
    }

    public Employee(int age, String whatever, String name) {
        Age = age;
        this.whatever = whatever;
        this.name = name;
    }

    private int Age;
    private String whatever;
    private String name;
}
