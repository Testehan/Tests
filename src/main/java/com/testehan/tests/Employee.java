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

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int calculateYearlySalary(){
        return this.salary * 12;
    }
}
