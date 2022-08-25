package com.testehan.tests;

public class Employee {
    private String name;
    private int salary;

    public Employee(){}

    public Employee(final String name, final int salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public int getSalary() {
        return salary;
    }

    public Employee setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public int calculateYearlySalary(){
        return this.salary * 12;
    }
}
