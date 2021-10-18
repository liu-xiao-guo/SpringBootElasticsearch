package com.liuxg.springbootelasticsearch.entity;

public class Employee {
    private String name;
    private String sex;
    private String occupation;
    private int salary;

    public Employee(String name, String sex, String occupation, int salary) {
        this.name = name;
        this.sex = sex;
        this.occupation = occupation;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getSex() {
        return sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
