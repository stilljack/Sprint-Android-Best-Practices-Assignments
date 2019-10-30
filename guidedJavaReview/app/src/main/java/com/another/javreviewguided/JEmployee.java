package com.another.javreviewguided;

public class JEmployee extends Employee {


    @Override
    public String toString() {
        return "JEmployee{" +
                "salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public JEmployee(int age, String whatever, int salary, String position) {
        super(age, whatever);
        this.salary = salary;
        this.position = position;
    }

    private int salary;
private String position;


    public JEmployee(int age, String whatever) {
        super(age, whatever);
    }
}